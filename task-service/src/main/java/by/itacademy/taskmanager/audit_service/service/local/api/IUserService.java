package by.itacademy.taskmanager.audit_service.service.local.api;

import by.itacademy.taskmanager.audit_service.dao.entity.User;

import java.util.UUID;

public interface IUserService {
    User create(User createDTO);

    User getCard(UUID uuid);
}
