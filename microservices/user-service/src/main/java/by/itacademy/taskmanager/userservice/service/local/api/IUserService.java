package by.itacademy.taskmanager.userservice.service.local.api;

import by.itacademy.taskmanager.userservice.core.dto.local.PageGetterParamsDTO;
import by.itacademy.taskmanager.userservice.core.dto.local.UpdateParamsDTO;
import by.itacademy.taskmanager.userservice.core.dto.local.UserCreateDTO;
import by.itacademy.taskmanager.userservice.core.dto.local.UserUpdateDTO;
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