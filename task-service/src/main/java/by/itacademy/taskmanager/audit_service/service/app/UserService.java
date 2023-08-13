package by.itacademy.taskmanager.audit_service.service.app;

import by.itacademy.taskmanager.audit_service.core.exceptions.NoSuchUserException;
import by.itacademy.taskmanager.audit_service.dao.api.IUserDao;
import by.itacademy.taskmanager.audit_service.dao.entity.User;
import by.itacademy.taskmanager.audit_service.service.app.api.IUserService;
import by.itacademy.taskmanager.audit_service.service.user.api.IUserGetterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor

@Service
public class UserService implements IUserService {

    private IUserDao userDao;

    private IUserGetterService userGetterService;

    @Transactional
    @Override
    public User create(User user) {

        if (userDao.findByUuid(user.getUuid()) != null){
            return user;
        }

        if (userGetterService.get(user.getUuid()) == null){
            throw new NoSuchUserException();
        }

        return userDao.save(user);
    }

    @Override
    public User getCard(UUID uuid) {
        return userDao.findByUuid(uuid);
    }
}
