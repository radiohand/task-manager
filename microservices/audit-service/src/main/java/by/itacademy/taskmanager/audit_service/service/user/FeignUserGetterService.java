package by.itacademy.taskmanager.audit_service.service.user;

import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;
import by.itacademy.taskmanager.audit_service.service.user.api.IUserClient;
import by.itacademy.taskmanager.audit_service.service.user.api.IUserGetterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class FeignUserGetterService implements IUserGetterService {

    private final IUserClient userClient;

    @Override
    public UserDTO get(String email) {
        return userClient.get(email);
    }
}