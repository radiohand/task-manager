package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.app.ProjectRefDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.UserRefDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.task.TaskDto;
import by.itacademy.taskmanager.audit_service.dao.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;


@RequiredArgsConstructor

public class TaskToTaskDtoConverter implements Converter<Task, TaskDto> {

    private final ConversionService conversionService;

    @Override
    public TaskDto convert(Task source) {

        TaskDto taskDto = new TaskDto();

        taskDto.setUuid(source.getUuid());
        taskDto.setDtCreate(conversionService.convert(source.getDtCreate(), Long.class));
        taskDto.setDtUpdate(conversionService.convert(source.getDtUpdate(), Long.class));

        ProjectRefDto dto = new ProjectRefDto();
        dto.setUuid(source.getProject().getUuid());

        taskDto.setProject(dto);
        taskDto.setTitle(source.getTitle());
        taskDto.setDescription(source.getDescription());
        taskDto.setTaskStatus(source.getTaskStatus());
        taskDto.setImplementer(conversionService.convert(source.getImplementer(), UserRefDto.class));

        return taskDto;
    }
}
