package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.local.UserRefDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.project.ProjectCreateDto;
import by.itacademy.taskmanager.audit_service.core.enums.ProjectStatus;
import by.itacademy.taskmanager.audit_service.dao.entity.Project;
import by.itacademy.taskmanager.audit_service.dao.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

public class ProjectCreateDtoToProjectConverter implements Converter<ProjectCreateDto, Project> {

    private final ConversionService conversionService;

    @Override
    public Project convert(ProjectCreateDto source) {

        Project project = new Project();

        project.setName(source.getName());
        project.setDescription(source.getDescription());

        project.setManager(conversionService.convert(source.getManager(), User.class));

        List<User> staff = new ArrayList<>();
        for (UserRefDto refDto : source.getStaff()) {
            staff.add(conversionService.convert(refDto, User.class));
        }

        project.setStaff(staff);
        project.setProjectStatus(ProjectStatus.valueOf(source.getProjectStatus()));

        return project;
    }
}
