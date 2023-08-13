package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.app.UserRefDto;
import by.itacademy.taskmanager.audit_service.dao.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

public class UserToUserRefDtoConverter implements Converter<User, UserRefDto> {

    @Override
    public UserRefDto convert(User source) {

        UserRefDto userDto = new UserRefDto();

        userDto.setUuid(source.getUuid());

        return userDto;
    }
}
