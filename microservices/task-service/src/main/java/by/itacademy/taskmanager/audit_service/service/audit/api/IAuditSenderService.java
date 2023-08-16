package by.itacademy.taskmanager.audit_service.service.audit.api;

import by.itacademy.taskmanager.audit_service.core.dto.audit.AuditDTO;

public interface IAuditSenderService {
    void send(AuditDTO dto);
}
