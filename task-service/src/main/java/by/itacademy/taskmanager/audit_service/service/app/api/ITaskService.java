package by.itacademy.taskmanager.audit_service.service.app.api;

import by.itacademy.taskmanager.audit_service.core.dto.app.*;
import by.itacademy.taskmanager.audit_service.core.dto.app.task.TaskCreateDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.task.TaskStatusUpdateDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.task.TaskUpdateDto;
import by.itacademy.taskmanager.audit_service.dao.entity.Task;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ITaskService {
    Task create(TaskCreateDto createDTO);
    Task getCard(UUID uuid);
    Page<Task> getPage(PageGetterParamsDto paramsDTO, PageTaskFilterDto filterDto);

    Task update(UpdateParamsDto paramsDto, TaskUpdateDto updateDto);

    Task updateStatus(UpdateParamsDto paramsDto, TaskStatusUpdateDto status);

    Task create(Task task);

    Task update(Task task);
}
