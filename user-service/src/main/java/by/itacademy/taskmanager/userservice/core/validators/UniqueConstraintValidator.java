package by.itacademy.taskmanager.userservice.core.validators;

import by.itacademy.taskmanager.userservice.core.validators.annotations.UniqueEmail;
import by.itacademy.taskmanager.userservice.dao.api.IUserDao;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class UniqueConstraintValidator implements ConstraintValidator<UniqueEmail, String> {

    private final IUserDao dao;

    @Override
    @Transactional
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return dao.findByEmail(value) == null;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
