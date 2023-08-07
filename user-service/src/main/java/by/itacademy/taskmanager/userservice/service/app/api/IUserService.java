package by.itacademy.taskmanager.userservice.service.app.api;

import by.itacademy.taskmanager.userservice.core.dto.app.PageGetterParamsDTO;
import by.itacademy.taskmanager.userservice.core.dto.app.UpdateParamsDTO;
import by.itacademy.taskmanager.userservice.core.dto.app.UserCreateDTO;
import by.itacademy.taskmanager.userservice.core.dto.app.UserUpdateDTO;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IUserService {
    User create(UserCreateDTO createDTO);

    User getCard(UUID uuid);

    User getCardByEmail(String email);

    Page<User> getPage(PageGetterParamsDTO params);

    User update(UpdateParamsDTO params, UserUpdateDTO updateDTO);

    User create(User user);

    User update(User user);


}
