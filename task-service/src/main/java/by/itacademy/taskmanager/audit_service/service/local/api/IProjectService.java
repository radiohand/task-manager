package by.itacademy.taskmanager.audit_service.service.app.api;

import by.itacademy.taskmanager.audit_service.core.dto.app.PageGetterParamsDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.project.ProjectCreateDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.project.ProjectUpdateDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.UpdateParamsDto;
import by.itacademy.taskmanager.audit_service.dao.entity.Project;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IProjectService {
    Project create(ProjectCreateDto createDTO);

    Project getCard(UUID uuid);
    Page<Project> getPage(PageGetterParamsDto paramsDTO, Boolean archived);

    Project update(UpdateParamsDto paramsDto, ProjectUpdateDto updateDto);

    Project create(Project project);

    Project update(Project project);
}
