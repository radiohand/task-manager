package by.itacademy.taskmanager.audit_service.endpoints.internal;

import by.itacademy.taskmanager.audit_service.core.dto.local.AuditCreateDTO;
import by.itacademy.taskmanager.audit_service.service.local.api.IAuditService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class KafkaAuditHandler {

    private IAuditService auditService;

    @KafkaListener(topics = "auditTopic")
    public void handleAudit(AuditCreateDTO dto){
        auditService.create(dto);
    }
}
