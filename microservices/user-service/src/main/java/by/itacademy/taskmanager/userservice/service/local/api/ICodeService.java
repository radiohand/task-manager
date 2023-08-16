package by.itacademy.taskmanager.userservice.service.local.api;

import by.itacademy.taskmanager.userservice.dao.entity.Code;
import by.itacademy.taskmanager.userservice.dao.entity.User;

import java.util.List;
import java.util.UUID;

public interface ICodeService {

    Code create(User user);

    List<Code> getAllForUser(User user);
}