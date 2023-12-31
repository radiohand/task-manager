package by.itacademy.taskmanager.audit_service.service.local;

import by.itacademy.taskmanager.audit_service.core.dto.local.*;
import by.itacademy.taskmanager.audit_service.core.dto.local.task.TaskCreateDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.task.TaskStatusUpdateDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.task.TaskUpdateDto;
import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;
import by.itacademy.taskmanager.audit_service.core.enums.UserRole;
import by.itacademy.taskmanager.audit_service.core.exceptions.custom.*;
import by.itacademy.taskmanager.audit_service.dao.api.ITaskDao;
import by.itacademy.taskmanager.audit_service.dao.entity.Project;
import by.itacademy.taskmanager.audit_service.dao.entity.Task;
import by.itacademy.taskmanager.audit_service.dao.entity.User;
import by.itacademy.taskmanager.audit_service.service.local.api.IProjectService;
import by.itacademy.taskmanager.audit_service.service.security.UserHolder;
import by.itacademy.taskmanager.audit_service.service.user.api.IUserGetterService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import by.itacademy.taskmanager.audit_service.service.local.api.ITaskService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@AllArgsConstructor
@Service
public class TaskService implements ITaskService {

    private ITaskDao dao;

    private IProjectService projectService;
    private ConversionService conversionService;

    private Validator validator;

    private UserHolder userHolder;

    private IUserGetterService userGetterService;

    @Transactional
    @Override
    public Task create(TaskCreateDto createDTO) {
        validate(createDTO);
        Project projectOfTask = projectService.getCard(createDTO.getProject().getUuid());

        if (projectOfTask == null){
            throw new NoSuchProjectException();
        }

        if (!hasAccess(projectOfTask)){
            throw new AccessDeniedException("Denied. No access to the selected project");
        }

        if (!projectOfTask
                .getStaff()
                .contains(conversionService.convert(createDTO.getImplementer(), User.class))){
            throw new NoSuchUserException("Specified implementer does not exist in the project of task");
        }
        Task task = conversionService.convert(createDTO, Task.class);
        Objects.requireNonNull(task).setUuid(UUID.randomUUID());
        return create(task);
    }

    @Transactional(readOnly = true)
    @Override
    public Task getCard(UUID uuid) {
        Task task = dao.findByUuid(uuid);
        if (task == null){
            throw new NoSuchTaskException();
        }

        if (!hasAccess(task)){return null;}

        return task;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Task> getPage(PageGetterParamsDto paramsDTO, PageTaskFilterDto filterDto) {
        validate(paramsDTO);
        validate(filterDto);
        List<Task> tasks;

        UserDTO userDTO = userGetterService.get(userHolder.getUser().getUsername());
        UUID userUuid = userDTO.getUuid();

        if (userDTO.getRole().equals(UserRole.ADMIN)){
            tasks = dao.findAll();
        } else {
            tasks = dao.findAllForUser(userUuid);
        }

        if (filterDto.getStatus() != null && !filterDto.getStatus().isEmpty()){
            List<String> statusList = filterDto.getStatus();
            tasks = tasks
                    .stream()
                    .filter(task -> statusList.contains(task.getTaskStatus().toString()))
                    .toList();
        }
        if (filterDto.getProject() != null && !filterDto.getProject().isEmpty()){
            List<ProjectRefDto> projectsList = filterDto.getProject();
            tasks = tasks
                    .stream()
                    .filter(task -> projectsList.contains(new ProjectRefDto(task.getProject().getUuid())))
                    .toList();
        }
        if (filterDto.getImplementer() != null && !filterDto.getImplementer().isEmpty()){
            List<UserRefDto> implementerList = filterDto.getImplementer();
            tasks = tasks
                    .stream()
                    .filter(task -> implementerList.contains(new UserRefDto(task.getImplementer().getUuid())))
                    .toList();
        }

        return new PageImpl<>(tasks);
    }

    @Transactional
    @Override
    public Task update(UpdateParamsDto params, TaskUpdateDto updateDTO) {
        validate(params);
        validate(updateDTO);

        Task currentTask = getCard(params.getUuid());

        if (currentTask == null){
            throw new NoSuchTaskException();
        }

        Project newProjectOfTask = projectService.getCard(updateDTO.getProject().getUuid());

        if (newProjectOfTask == null){
            throw new NoSuchProjectException();
        }

        if (!hasAccess(currentTask)){
            throw new AccessDeniedException("Denied. No access to the selected task");
        }

        if (!currentTask.getDtUpdate().equals(params.getVersion())){
            throw new OutdatedVersionException("Denied. Specified version is not relevant");
        }

        Task taskToSave = conversionService.convert(updateDTO, Task.class);
        Objects.requireNonNull(taskToSave).setUuid(params.getUuid());
        taskToSave.setDtCreate(currentTask.getDtCreate());

        return update(taskToSave);
    }
    @Transactional
    @Override
    public void updateStatus(UpdateParamsDto paramsDto, TaskStatusUpdateDto updateDto) {
        validate(paramsDto);
        validate(updateDto);

        Task currentTask = getCard(paramsDto.getUuid());

        if (!hasAccess(currentTask)){
            throw new AccessDeniedException("Denied. No access to the selected task");
        }

        if (!currentTask.getDtUpdate().equals(paramsDto.getVersion())){
            throw new OutdatedVersionException("Denied. Specified version is not relevant");
        }

        dao.updateStatus(paramsDto.getUuid(), updateDto.getTaskStatus());
    }

    @Transactional
    @Override
    public Task create(Task task) {
        return dao.save(task);
    }

    @Transactional
    @Override
    public  Task update(Task task) {
        return dao.save(task);
    }

    private <T> void validate(T dto){
        Set<ConstraintViolation<T>> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private boolean hasAccess(Task task){
        return hasAccess(task.getProject());
    }

    private boolean hasAccess(Project project){
        UserDTO userDTO = userGetterService.get(userHolder.getUser().getUsername());
        UserRole role = userDTO.getRole();
        UUID userUuid = userDTO.getUuid();
        return role.equals(UserRole.ADMIN) ||
                project.getManager().getUuid().equals(userUuid) ||
                project.getStaff().contains(new User(userUuid));
    }
}