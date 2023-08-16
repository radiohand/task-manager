package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.local.UserRefDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.project.ProjectDto;
import by.itacademy.taskmanager.audit_service.dao.entity.Project;
import by.itacademy.taskmanager.audit_service.dao.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

public class ProjectToProjectDtoConverter implements Converter<Project, ProjectDto> {

    private final ConversionService conversionService;

    @Override
    public ProjectDto convert(Project source) {

        ProjectDto projectDto = new ProjectDto();

        projectDto.setUuid(source.getUuid());
        projectDto.setDtCreate(conversionService.convert(source.getDtCreate(), Long.class));
        projectDto.setDtUpdate(conversionService.convert(source.getDtUpdate(), Long.class));

        projectDto.setName(source.getName());
        projectDto.setDescription(source.getDescription());
        projectDto.setManager(conversionService.convert(source.getManager(), UserRefDto.class));

        List<UserRefDto> staff = new ArrayList<>();
        for (User user : source.getStaff()) {
            staff.add(conversionService.convert(user, UserRefDto.class));
        }

        projectDto.setStaff(staff);
        projectDto.setProjectStatus(source.getProjectStatus());

        return projectDto;
    }
}
