package by.itacademy.taskmanager.userservice.service.app.api;

import by.itacademy.taskmanager.userservice.core.dto.app.LoginDTO;
import by.itacademy.taskmanager.userservice.core.dto.app.UserRegistrationDTO;
import by.itacademy.taskmanager.userservice.core.dto.app.VerificationParamsDTO;
import by.itacademy.taskmanager.userservice.dao.entity.User;

public interface IAuthenticationService {
    User register(UserRegistrationDTO registrationDTO);

    void verify(VerificationParamsDTO params);

    String login(LoginDTO dto);

    User getMe();
}
