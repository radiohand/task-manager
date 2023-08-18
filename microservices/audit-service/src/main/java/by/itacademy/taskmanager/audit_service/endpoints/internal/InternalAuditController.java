package by.itacademy.taskmanager.audit_service.endpoints.internal;

import by.itacademy.taskmanager.audit_service.service.local.api.IAuditService;
import by.itacademy.taskmanager.audit_service.core.dto.local.AuditCreateDTO;
import by.itacademy.taskmanager.audit_service.core.dto.local.AuditDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
public class InternalAuditController {

    private final IAuditService service;

    private final ConversionService conversionService;

    @PostMapping(value = "/app/audit")
    public ResponseEntity<AuditDTO> create(@RequestBody AuditCreateDTO createDTO){
        return new ResponseEntity<>(conversionService.convert(service.create(createDTO), AuditDTO.class), HttpStatus.CREATED);
    }
}