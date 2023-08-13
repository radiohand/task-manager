package by.itacademy.taskmanager.audit_service.endpoints.web.controllers;

import by.itacademy.taskmanager.audit_service.core.dto.app.*;
import by.itacademy.taskmanager.audit_service.core.dto.app.project.ProjectDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.project.ProjectUpdateDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.task.*;
import by.itacademy.taskmanager.audit_service.core.enums.TaskStatus;
import by.itacademy.taskmanager.audit_service.dao.entity.Task;
import lombok.AllArgsConstructor;
import by.itacademy.taskmanager.audit_service.service.app.api.ITaskService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor

@RestController
public class TaskController {

    private final ITaskService service;

    private final ConversionService conversionService;

    @PostMapping("/task")
    public ResponseEntity<TaskDto> create(@RequestBody TaskCreateDto createDto){
        return new ResponseEntity<>(conversionService.convert(service.create(createDto), TaskDto.class),
                HttpStatus.CREATED);
    }

    @GetMapping("/task")
    public ResponseEntity<PageDto<TaskDto>> getPage(@RequestParam (defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "20") Integer size,
                                                     @RequestParam(required = false) List<ProjectRefDto> project,
                                                     @RequestParam(required = false) List<UserRefDto> implementer,
                                                     @RequestParam(required = false) List<String> status){
        return ResponseEntity
                .ok(conversionService
                        .convert((service.getPage(new PageGetterParamsDto(page, size),
                                        new PageTaskFilterDto(project, implementer, status))),
                                PageTaskDto.class)
                );
    }

    @GetMapping(value = "/task/{uuid}")
    public ResponseEntity<TaskDto> getCard(@PathVariable UUID uuid){
        return ResponseEntity.ok(conversionService.convert(service.getCard(uuid), TaskDto.class));
    }

    @PutMapping("/task/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<TaskDto> update(@PathVariable UUID uuid,
                                           @PathVariable LocalDateTime dtUpdate,
                                           @RequestBody TaskUpdateDto updateDto){
        return ResponseEntity
                .ok(conversionService
                        .convert(service.update(new UpdateParamsDto(uuid, dtUpdate), updateDto),
                                TaskDto.class));
    }

    @PatchMapping("/task/{uuid}/dt_update/{dt_update}/status/{status}")
    public ResponseEntity<TaskDto> updateStatus(@PathVariable UUID uuid,
                                          @PathVariable LocalDateTime dtUpdate,
                                          @PathVariable String status){
        return ResponseEntity
                .ok(conversionService
                        .convert(service.updateStatus(new UpdateParamsDto(uuid, dtUpdate),
                                        new TaskStatusUpdateDto(status)),
                                TaskDto.class));
    }
}