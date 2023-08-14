package by.itacademy.taskmanager.audit_service.service.audit.api;

import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;
import by.itacademy.taskmanager.audit_service.dao.entity.Project;

public interface IAuditService {
    void send(Project target, UserDTO performer, String text);
}
