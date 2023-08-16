package by.itacademy.taskmanager.userservice.dao.api;

import by.itacademy.taskmanager.userservice.dao.entity.Code;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ICodeDao extends JpaRepository<Code, UUID> {
    List<Code>findAllByUser(User user);
}