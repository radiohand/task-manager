package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.app.UserRefDto;
import by.itacademy.taskmanager.audit_service.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserRefDtoToUserConverter implements Converter<UserRefDto, User> {

    @Override
    public User convert(UserRefDto source) {

        User user = new User();

        user.setUuid(source.getUuid());

        return user;
    }
}
