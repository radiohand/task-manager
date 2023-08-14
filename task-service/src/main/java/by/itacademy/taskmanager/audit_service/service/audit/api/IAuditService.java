package by.itacademy.taskmanager.userservice.service.audit.api;

import by.itacademy.taskmanager.userservice.dao.entity.User;

public interface IAuditService {
    void send(User target, User performer, String text);
}
