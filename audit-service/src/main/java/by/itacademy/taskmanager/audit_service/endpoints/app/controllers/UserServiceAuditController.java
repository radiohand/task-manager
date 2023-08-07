package by.itacademy.taskmanager.audit_service.endpoints.app.controllers;

import by.itacademy.taskmanager.audit_service.service.app.api.IAuditService;
import by.itacademy.taskmanager.audit_service.core.dto.app.AuditCreateDTO;
import by.itacademy.taskmanager.audit_service.core.dto.app.AuditDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
public class UserServiceAuditController {

    private final IAuditService service;

    private final ConversionService conversionService;

    @PostMapping(value = "/app/audit")
    public ResponseEntity<AuditDTO> create(@RequestBody AuditCreateDTO createDTO){
        return new ResponseEntity<>(conversionService.convert(service.create(createDTO), AuditDTO.class), HttpStatus.CREATED);
    }
}