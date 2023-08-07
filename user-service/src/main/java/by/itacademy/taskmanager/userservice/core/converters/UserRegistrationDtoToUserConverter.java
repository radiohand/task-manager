package by.itacademy.taskmanager.userservice.core.converters;

import by.itacademy.taskmanager.userservice.core.dto.app.UserRegistrationDTO;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor

public class UserRegistrationDtoToUserConverter implements Converter<UserRegistrationDTO, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User convert(UserRegistrationDTO source) {

        User user = new User();

        user.setEmail(source.getEmail());
        user.setFio(source.getFio());
        user.setPassword(passwordEncoder.encode(source.getPassword()));

        return user;
    }
}
