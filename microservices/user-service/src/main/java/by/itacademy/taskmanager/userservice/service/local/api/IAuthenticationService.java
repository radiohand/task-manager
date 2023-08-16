package by.itacademy.taskmanager.userservice.service.local.api;

import by.itacademy.taskmanager.userservice.core.dto.local.LoginDTO;
import by.itacademy.taskmanager.userservice.core.dto.local.UserRegistrationDTO;
import by.itacademy.taskmanager.userservice.core.dto.local.VerificationParamsDTO;
import by.itacademy.taskmanager.userservice.dao.entity.User;

public interface IAuthenticationService {
    User register(UserRegistrationDTO registrationDTO);

    void verify(VerificationParamsDTO params);

    String login(LoginDTO dto);

    User getMe();
}
