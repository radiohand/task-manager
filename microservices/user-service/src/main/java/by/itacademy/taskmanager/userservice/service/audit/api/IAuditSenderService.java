package by.itacademy.taskmanager.userservice.service.audit.api;

import by.itacademy.taskmanager.userservice.core.dto.audit.AuditDTO;

public interface IAuditSenderService {
    void send(AuditDTO dto);
}
