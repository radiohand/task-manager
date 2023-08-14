package by.itacademy.taskmanager.audit_service.endpoints.web.controllers;

import by.itacademy.taskmanager.audit_service.core.dto.local.PageDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.PageGetterParamsDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.UpdateParamsDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.project.PageProjectDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.project.ProjectCreateDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.project.ProjectDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.project.ProjectUpdateDto;
import by.itacademy.taskmanager.audit_service.service.local.api.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor

@RestController
public class ProjectController {

    private final IProjectService service;

    private final ConversionService conversionService;

    @PostMapping("/project")
    public ResponseEntity<ProjectDto> create(@RequestBody ProjectCreateDto createDto){
        return new ResponseEntity<>(conversionService.convert(service.create(createDto), ProjectDto.class),
                HttpStatus.CREATED);
    }

    @GetMapping("/project")
    public ResponseEntity<PageDto<ProjectDto>> getPage(@RequestParam (defaultValue = "0") Integer page,
                                                       @RequestParam(defaultValue = "20") Integer size,
                                                       @RequestParam(defaultValue = "false") Boolean archived){
        return ResponseEntity
                .ok(conversionService
                        .convert((service.getPage(new PageGetterParamsDto(page, size), archived)), PageProjectDto.class)
                );
    }

    @GetMapping(value = "/project/{uuid}")
    public ResponseEntity<ProjectDto> getCard(@PathVariable UUID uuid){
        return ResponseEntity.ok(conversionService.convert(service.getCard(uuid), ProjectDto.class));
    }

    @PutMapping("/project/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<ProjectDto> update(@PathVariable UUID uuid,
                                             @PathVariable("dt_update") LocalDateTime dtUpdate,
                                             @RequestBody ProjectUpdateDto updateDto){
        return ResponseEntity
                .ok(conversionService
                        .convert(service.update(new UpdateParamsDto(uuid, dtUpdate), updateDto),
                ProjectDto.class));
    }
}