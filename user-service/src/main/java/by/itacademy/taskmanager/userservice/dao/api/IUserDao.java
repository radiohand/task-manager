package by.itacademy.taskmanager.userservice.dao.api;

import by.itacademy.taskmanager.userservice.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserDao extends JpaRepository<User, Long> {
    User findByUuid (UUID uuid);

    User findByEmail (String email);
}