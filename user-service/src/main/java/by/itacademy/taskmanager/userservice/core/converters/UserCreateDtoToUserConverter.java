package by.itacademy.taskmanager.userservice.core.converters;

import by.itacademy.taskmanager.userservice.core.dto.app.UserCreateDTO;
import by.itacademy.taskmanager.userservice.core.enums.UserRole;
import by.itacademy.taskmanager.userservice.core.enums.UserStatus;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor

public class UserCreateDtoToUserConverter implements Converter<UserCreateDTO, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User convert(UserCreateDTO source) {

        User user = new User();

        user.setEmail(source.getEmail());
        user.setFio(source.getFio());
        user.setRole(UserRole.valueOf(source.getRole()));
        user.setStatus(UserStatus.valueOf(source.getStatus()));
        user.setPassword(passwordEncoder.encode(source.getPassword()));

        return user;
    }
}
