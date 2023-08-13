package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.app.task.TaskCreateDto;
import by.itacademy.taskmanager.audit_service.core.enums.TaskStatus;
import by.itacademy.taskmanager.audit_service.dao.api.IProjectDao;
import by.itacademy.taskmanager.audit_service.dao.entity.Task;
import by.itacademy.taskmanager.audit_service.dao.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;


@RequiredArgsConstructor

public class TaskCreateDtoToTaskConverter implements Converter<TaskCreateDto, Task> {

    private final IProjectDao projectDao;
    private final ConversionService conversionService;

    @Override
    public Task convert(TaskCreateDto source) {

        Task task = new Task();

        task.setProject(projectDao.findByUuid(source.getProject().getUuid()));
        task.setTitle(source.getTitle());
        task.setDescription(source.getDescription());
        task.setTaskStatus(TaskStatus.valueOf(source.getTaskStatus()));
        task.setImplementer(conversionService.convert(source.getImplementer(), User.class));

        return task;
    }
}
