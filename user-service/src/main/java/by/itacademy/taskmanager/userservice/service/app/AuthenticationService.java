package by.itacademy.taskmanager.userservice.service.app;

import by.itacademy.taskmanager.userservice.core.dto.app.LoginDTO;
import by.itacademy.taskmanager.userservice.core.dto.app.UserRegistrationDTO;
import by.itacademy.taskmanager.userservice.core.dto.app.VerificationParamsDTO;
import by.itacademy.taskmanager.userservice.core.enums.UserRole;
import by.itacademy.taskmanager.userservice.core.enums.UserStatus;
import by.itacademy.taskmanager.userservice.core.exceptions.custom.InactiveAccountException;
import by.itacademy.taskmanager.userservice.core.exceptions.custom.WrongPasswordException;
import by.itacademy.taskmanager.userservice.core.exceptions.custom.WrongVerificationCodeException;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import by.itacademy.taskmanager.userservice.endpoints.web.utils.JwtTokenHandler;
import by.itacademy.taskmanager.userservice.service.app.api.IUserService;
import by.itacademy.taskmanager.userservice.service.audit.api.IAuditService;
import by.itacademy.taskmanager.userservice.service.app.api.IAuthenticationService;
import by.itacademy.taskmanager.userservice.service.email.api.IEmailSenderService;
import by.itacademy.taskmanager.userservice.service.security.UserHolder;
import feign.FeignException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor

@Service
public class AuthenticationService implements IAuthenticationService {

    private static final int MIN_CODE_VALUE = 100_000;
    private static final int MAX_CODE_VALUE = 999_999;

    private final IUserService userService;
    private final IEmailSenderService emailService;
    private final IAuditService auditService;

    private final ConversionService conversionService;
    private final Validator validator;

    private final UserDetailsService detailsService;
    private final UserHolder userHolder;
    private final JwtTokenHandler jwtTokenHandler;
    private final PasswordEncoder passwordEncoder;

    private final Random random = new Random();

    @Override
    @Transactional
    public User register(UserRegistrationDTO registrationDTO) {
        validate(registrationDTO);
        Integer verifyCode = generateCode();
        User createdUser;

        User user = conversionService.convert(registrationDTO, User.class);
        Objects.requireNonNull(user).setUuid(UUID.randomUUID());
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.WAITING_ACTIVATION);
        user.setVerifyCode(verifyCode);

        createdUser = userService.create(user);

        try{
            emailService.sendVerification(user.getEmail(), verifyCode.toString());
        } catch (MailException e){
            throw new RuntimeException(e);
        }

        try{
            auditService.send(createdUser,
                    userService.getCardByEmail(userHolder.getUser().getUsername()),
                    "New user registered");
        } catch (FeignException e){
            throw new RuntimeException(e);
        }


        return createdUser;
    }

    @Override
    @Transactional
    public void verify(VerificationParamsDTO params) {
        User user = userService.getCardByEmail(params.getMail());
        if (user.getVerifyCode().equals(params.getCode())){
            user.setStatus(UserStatus.ACTIVATED);
            userService.update(user);
        }
        else throw new WrongVerificationCodeException();
    }

    public String login(LoginDTO dto){
        UserDetails userDetails = detailsService.loadUserByUsername(dto.getEmail());
        if(!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())){
            throw new WrongPasswordException();
        }
        if (!userDetails.isAccountNonLocked()){
            throw new InactiveAccountException();
        }
        return jwtTokenHandler.generateAccessToken(userDetails);
    }

    public User getMe(){
        return userService.getCardByEmail(userHolder.getUser().getUsername());
    }

    private <T> void validate(T dto){
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private Integer generateCode(){
        return random.nextInt(MAX_CODE_VALUE - MIN_CODE_VALUE) + MIN_CODE_VALUE;
    }
}
