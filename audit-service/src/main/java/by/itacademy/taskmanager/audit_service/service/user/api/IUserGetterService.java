package by.itacademy.taskmanager.audit_service.service.user.api;

import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;

public interface IUserGetterService {
    UserDTO get(String email);
}
