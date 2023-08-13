package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.app.project.PageProjectDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.project.ProjectDto;
import by.itacademy.taskmanager.audit_service.dao.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

public class PageProjectToPageDtoConverter implements Converter<Page<Project>, PageProjectDto> {

    private final ConversionService conversionService;

    @Override
    public PageProjectDto convert(Page<Project> source) {

        PageProjectDto pageDTO = new PageProjectDto();
        List<ProjectDto> projectDtos = new ArrayList<>();

        for (Project project : source.getContent()) {
            projectDtos.add(conversionService.convert(project, ProjectDto.class));
        }

        pageDTO.setNumber(source.getNumber());
        pageDTO.setSize(source.getSize());

        pageDTO.setTotalPages(source.getTotalPages());
        pageDTO.setTotalElements(source.getTotalElements());

        pageDTO.setFirst(source.isFirst());
        pageDTO.setNumberOfElements(source.getNumberOfElements());
        pageDTO.setLast(source.isLast());

        pageDTO.setContent(projectDtos);

        return pageDTO;
    }
}
