package by.itacademy.taskmanager.userservice.core.converters;

import by.itacademy.taskmanager.userservice.core.dto.local.UserDTO;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor

public class UserToUserDtoConverter implements Converter<User, UserDTO> {

    private final ConversionService conversionService;

    @Override
    public UserDTO convert(User source) {

        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(source.getUuid());
        userDTO.setDtCreate(conversionService.convert(source.getDtCreate(), Long.class));
        userDTO.setDtUpdate(conversionService.convert(source.getDtUpdate(), Long.class));
        userDTO.setEmail(source.getEmail());
        userDTO.setFio(source.getFio());
        userDTO.setRole(source.getRole());
        userDTO.setStatus(source.getStatus());

        return userDTO;
    }
}
