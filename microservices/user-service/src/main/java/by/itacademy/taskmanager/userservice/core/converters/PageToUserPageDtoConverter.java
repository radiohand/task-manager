package by.itacademy.taskmanager.userservice.core.converters;

import by.itacademy.taskmanager.userservice.core.dto.local.UserDTO;
import by.itacademy.taskmanager.userservice.core.dto.local.UserPageDTO;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

public class PageToUserPageDtoConverter implements Converter<Page<User>, UserPageDTO> {

    private final ConversionService conversionService;

    @Override
    public UserPageDTO convert(Page<User> source) {
        UserPageDTO pageDTO = new UserPageDTO();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : source.getContent()) {
            userDTOS.add(conversionService.convert(user, UserDTO.class));
        }

        pageDTO.setNumber(source.getNumber());
        pageDTO.setSize(source.getSize());

        pageDTO.setTotalPages(source.getTotalPages());
        pageDTO.setTotalElements(source.getTotalElements());

        pageDTO.setFirst(source.isFirst());
        pageDTO.setNumberOfElements(source.getNumberOfElements());
        pageDTO.setLast(source.isLast());

        pageDTO.setContent(userDTOS);

        return pageDTO;
    }
}
