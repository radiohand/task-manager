package by.itacademy.taskmanager.audit_service.service.user.api;

import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;

import java.util.UUID;

public interface IUserGetterService {
    UserDTO get(String email);

    UserDTO get(UUID uuid);
}
