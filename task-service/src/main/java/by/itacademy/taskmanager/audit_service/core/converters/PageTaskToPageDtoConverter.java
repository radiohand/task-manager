package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.app.task.PageTaskDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.task.TaskDto;
import by.itacademy.taskmanager.audit_service.dao.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

public class PageTaskToPageDtoConverter implements Converter<Page<Task>, PageTaskDto> {

    private final ConversionService conversionService;

    @Override
    public PageTaskDto convert(Page<Task> source) {

        PageTaskDto pageDTO = new PageTaskDto();
        List<TaskDto> taskDtos = new ArrayList<>();

        for (Task task : source.getContent()) {
            taskDtos.add(conversionService.convert(task, TaskDto.class));
        }

        pageDTO.setNumber(source.getNumber());
        pageDTO.setSize(source.getSize());

        pageDTO.setTotalPages(source.getTotalPages());
        pageDTO.setTotalElements(source.getTotalElements());

        pageDTO.setFirst(source.isFirst());
        pageDTO.setNumberOfElements(source.getNumberOfElements());
        pageDTO.setLast(source.isLast());

        pageDTO.setContent(taskDtos);

        return pageDTO;
    }
}
