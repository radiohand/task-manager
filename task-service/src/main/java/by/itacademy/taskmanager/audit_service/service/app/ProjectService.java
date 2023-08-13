package by.itacademy.taskmanager.audit_service.service.app;

import by.itacademy.taskmanager.audit_service.core.dto.app.*;
import by.itacademy.taskmanager.audit_service.core.dto.app.project.ProjectCreateDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.project.ProjectUpdateDto;
import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;
import by.itacademy.taskmanager.audit_service.core.enums.ProjectStatus;
import by.itacademy.taskmanager.audit_service.core.exceptions.NoSuchProjectException;
import by.itacademy.taskmanager.audit_service.core.exceptions.OutdatedVersionException;
import by.itacademy.taskmanager.audit_service.dao.api.IProjectDao;
import by.itacademy.taskmanager.audit_service.dao.entity.Project;
import by.itacademy.taskmanager.audit_service.dao.entity.User;
import by.itacademy.taskmanager.audit_service.service.app.api.IProjectService;
import by.itacademy.taskmanager.audit_service.service.app.api.IUserService;
import by.itacademy.taskmanager.audit_service.service.security.UserHolder;
import by.itacademy.taskmanager.audit_service.service.user.api.IUserGetterService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
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

        return create(project);
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
        if (!currentProject.getDtUpdate().truncatedTo(ChronoUnit.SECONDS).equals(params.getVersion())){
            throw new OutdatedVersionException("Отказано. Указана неактуальная версия");
        }
        Project projectToSave = conversionService.convert(updateDTO, Project.class);
        Objects.requireNonNull(projectToSave).setUuid(params.getUuid());
        projectToSave.setDtCreate(currentProject.getDtCreate());

        userService.create(projectToSave.getManager());
        for (User staff : projectToSave.getStaff()) {
            userService.create(staff);
        }

//        try{
//            auditService.send(updatedUser,
//                    getCardByEmail(userHolder.getUser().getUsername()),
//                    "User updated");
//        } catch (FeignException e){
//            throw new RuntimeException(e);
//        }

        return update(projectToSave);
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
}