package by.itacademy.taskmanager.userservice.service.local;

import by.itacademy.taskmanager.userservice.core.dto.local.PageGetterParamsDTO;
import by.itacademy.taskmanager.userservice.core.dto.local.UpdateParamsDTO;
import by.itacademy.taskmanager.userservice.core.dto.local.UserUpdateDTO;
import by.itacademy.taskmanager.userservice.core.exceptions.custom.OutdatedVersionException;
import by.itacademy.taskmanager.userservice.service.audit.api.IAuditService;
import by.itacademy.taskmanager.userservice.service.security.UserHolder;
import feign.FeignException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import by.itacademy.taskmanager.userservice.core.dto.local.UserCreateDTO;
import by.itacademy.taskmanager.userservice.dao.api.IUserDao;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import by.itacademy.taskmanager.userservice.service.local.api.IUserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService implements IUserService {

    private final IUserDao dao;
    private final IAuditService auditService;

    private final ConversionService conversionService;
    private final Validator validator;

    private final UserHolder userHolder;

    @Override
    @Transactional
    public User create(UserCreateDTO createDTO) {
        validate(createDTO);
        User user = conversionService.convert(createDTO, User.class);
        Objects.requireNonNull(user).setUuid(UUID.randomUUID());

        User createdUser = create(user);

        try{
            auditService.send(createdUser,
                    getCardByEmail(userHolder.getUser().getUsername()),
                    "New user created");
        } catch (FeignException e){
            throw new RuntimeException(e);
        }

        return createdUser;
    }

    @Override
    public User getCard(UUID uuid) {
        return dao.findByUuid(uuid);
    }

    @Override
    public User getCardByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public Page<User> getPage(PageGetterParamsDTO params) {
        validate(params);
        return dao.findAll(PageRequest.of(params.getPage(), params.getSize()));
    }

    @Override
    @Transactional
    public User update(UpdateParamsDTO params, UserUpdateDTO updateDTO) {
        validate(params);
        validate(updateDTO);
        User currentUser = getCard(params.getUuid());
        if (!currentUser.getDtUpdate().equals(params.getVersion())){
            throw new OutdatedVersionException("Отказано. Указана неактуальная версия");
        }
        User userToSave = conversionService.convert(updateDTO, User.class);
        Objects.requireNonNull(userToSave).setUuid(params.getUuid());
        userToSave.setDtCreate(currentUser.getDtCreate());

        User updatedUser = update(userToSave);

        try{
        auditService.send(updatedUser,
                getCardByEmail(userHolder.getUser().getUsername()),
                "User updated");
        } catch (FeignException e){
            throw new RuntimeException(e);
        }

        return updatedUser;
    }

    @Override
    @Transactional
    public User create(User user) {
        return dao.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        return dao.save(user);
    }


    private <T> void validate(T dto){
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}