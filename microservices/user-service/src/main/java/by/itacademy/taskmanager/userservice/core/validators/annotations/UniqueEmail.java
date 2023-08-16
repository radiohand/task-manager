package by.itacademy.taskmanager.userservice.core.validators.annotations;

import by.itacademy.taskmanager.userservice.core.validators.UniqueConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueConstraintValidator.class)
public @interface UniqueEmail {
    String message() default "Value isn't unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
