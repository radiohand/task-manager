package by.itacademy.taskmanager.audit_service.endpoints.web.controllers;

import by.itacademy.taskmanager.audit_service.core.dto.app.AuditDTO;
import by.itacademy.taskmanager.audit_service.core.dto.app.PageAuditDTO;
import by.itacademy.taskmanager.audit_service.core.dto.app.PageDTO;
import lombok.AllArgsConstructor;
import by.itacademy.taskmanager.audit_service.service.app.api.IAuditService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor

@RestController
public class AuditController {

    private final IAuditService service;

    private final ConversionService conversionService;

    @GetMapping("/audit")
    public ResponseEntity<PageDTO<AuditDTO>> getPage(@RequestParam (defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "20") Integer size) {
        return ResponseEntity.ok(conversionService.convert((service.getPage(page, size)), PageAuditDTO.class));
    }

    @GetMapping(value = "/audit/{uuid}")
    public ResponseEntity<AuditDTO> getCard(@PathVariable UUID uuid){
        return ResponseEntity.ok(conversionService.convert(service.getCard(uuid), AuditDTO.class));
    }
}