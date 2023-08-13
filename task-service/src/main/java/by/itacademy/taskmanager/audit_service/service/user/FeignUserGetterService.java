package by.itacademy.taskmanager.audit_service.service.user;

import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;
import by.itacademy.taskmanager.audit_service.service.user.api.IUserClient;
import by.itacademy.taskmanager.audit_service.service.user.api.IUserGetterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor

@Service
public class FeignUserGetterService implements IUserGetterService {

    private final IUserClient userClient;

    @Override
    public UserDTO get(String email) {
        return userClient.get(email);
    }

    @Override
    public UserDTO get(UUID uuid) {
        return userClient.get(uuid);
    }
}