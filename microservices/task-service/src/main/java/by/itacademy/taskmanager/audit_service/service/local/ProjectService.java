package by.itacademy.taskmanager.audit_service.service.local;

import by.itacademy.taskmanager.audit_service.core.dto.local.*;
import by.itacademy.taskmanager.audit_service.core.dto.local.project.ProjectCreateDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.project.ProjectUpdateDto;
import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;
import by.itacademy.taskmanager.audit_service.core.enums.ProjectStatus;
import by.itacademy.taskmanager.audit_service.core.enums.UserRole;
import by.itacademy.taskmanager.audit_service.core.exceptions.custom.AccessDeniedException;
import by.itacademy.taskmanager.audit_service.core.exceptions.custom.NoSuchProjectException;
import by.itacademy.taskmanager.audit_service.core.exceptions.custom.OutdatedVersionException;
import by.itacademy.taskmanager.audit_service.dao.api.IProjectDao;
import by.itacademy.taskmanager.audit_service.dao.entity.Project;
import by.itacademy.taskmanager.audit_service.dao.entity.Task;
import by.itacademy.taskmanager.audit_service.dao.entity.User;
import by.itacademy.taskmanager.audit_service.service.local.api.IProjectService;
import by.itacademy.taskmanager.audit_service.service.local.api.IUserService;
import by.itacademy.taskmanager.audit_service.service.audit.api.IAuditService;
import by.itacademy.taskmanager.audit_service.service.security.UserHolder;
import by.itacademy.taskmanager.audit_service.service.user.api.IUserGetterService;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ProjectService implements IProjectService {

    private IProjectDao dao;

    private IUserService userService;

    private ConversionService conversionService;

    private Validator validator;

    private UserHolder userHolder;

    private IUserGetterService userGetterService;

    private IAuditService auditService;


    @Transactional
    @Override
    public Project create(ProjectCreateDto createDTO) {
        validate(createDTO);
        Project project = conversionService.convert(createDTO, Project.class);
        Objects.requireNonNull(project).setUuid(UUID.randomUUID());

        userService.create(project.getManager());
        for (User staff : project.getStaff()) {
            userService.create(staff);
        }

        Project createdProject = create(project);

        UserDTO userDTO = userGetterService.get(userHolder.getUser().getUsername());

        try{
            auditService.send(createdProject,
                    userDTO,
                    "Project created");
        } catch (FeignException e){
            throw new RuntimeException(e);
        }

        return createdProject;
    }

    @Transactional(readOnly = true)
    @Override
    public Project getCard(UUID uuid) {
        Project project = dao.findByUuid(uuid);
        if (project == null){
            throw new NoSuchProjectException();
        }
        return project;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Project> getPage(PageGetterParamsDto paramsDTO, Boolean archived) {

        UserDTO userDTO = userGetterService.get(userHolder.getUser().getUsername());
        UUID userUuid = userDTO.getUuid();

        List<Project> projects = dao.findAllForUser(userUuid);

        if (!archived){
            projects = projects
                    .stream()
                    .filter((project) -> project.getProjectStatus().equals(ProjectStatus.ACTIVE))
                    .toList();
        }
        return new PageImpl<>(projects);
    }
    @Transactional
    @Override
    public Project update(UpdateParamsDto params, ProjectUpdateDto updateDTO) {
        validate(params);
        validate(updateDTO);

        Project currentProject = getCard(params.getUuid());

        if (!hasAccess(currentProject)){
            throw new AccessDeniedException("Denied. No access to the selected project");
        }

        if (!currentProject.getDtUpdate().equals(params.getVersion())){
            throw new OutdatedVersionException("Denied. Specified version is not relevant");
        }

        Project projectToSave = conversionService.convert(updateDTO, Project.class);
        Objects.requireNonNull(projectToSave).setUuid(params.getUuid());
        projectToSave.setDtCreate(currentProject.getDtCreate());

        userService.create(projectToSave.getManager());
        for (User staff : projectToSave.getStaff()) {
            userService.create(staff);
        }

        Project updatedProject = create(projectToSave);

        UserDTO userDTO = userGetterService.get(userHolder.getUser().getUsername());

        try{
            auditService.send(updatedProject,
                    userDTO,
                    "Project updated");
        } catch (FeignException e){
            throw new RuntimeException(e);
        }

        return updatedProject;
    }

    @Transactional
    @Override
    public Project create(Project project) {
        return dao.saveAndFlush(project);
    }

    @Transactional
    @Override
    public Project update(Project project) {
        return dao.saveAndFlush(project);
    }

    private <T> void validate(T dto){
        Set<ConstraintViolation<T>> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private boolean hasAccess(Project project){
        UserDTO userDTO = userGetterService.get(userHolder.getUser().getUsername());
        UserRole role = userDTO.getRole();
        UUID userUuid = userDTO.getUuid();
        return role.equals(UserRole.ADMIN) ||
                project.getManager().getUuid().equals(userUuid);
    }
}