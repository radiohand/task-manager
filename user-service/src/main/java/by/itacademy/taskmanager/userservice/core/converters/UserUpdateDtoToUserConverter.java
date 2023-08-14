package by.itacademy.taskmanager.userservice.core.converters;

import by.itacademy.taskmanager.userservice.core.dto.local.UserUpdateDTO;
import by.itacademy.taskmanager.userservice.core.enums.UserRole;
import by.itacademy.taskmanager.userservice.core.enums.UserStatus;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserUpdateDtoToUserConverter implements Converter<UserUpdateDTO, User> {
    @Override
    public User convert(UserUpdateDTO source) {

        User user = new User();

        user.setEmail(source.getEmail());
        user.setFio(source.getFio());
        user.setRole(UserRole.valueOf(source.getRole()));
        user.setStatus(UserStatus.valueOf(source.getStatus()));
        user.setPassword(source.getPassword());

        return user;
    }
}
