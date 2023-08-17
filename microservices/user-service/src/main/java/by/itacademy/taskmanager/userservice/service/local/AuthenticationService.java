package by.itacademy.taskmanager.userservice.service.local;

import by.itacademy.taskmanager.userservice.core.dto.local.LoginDTO;
import by.itacademy.taskmanager.userservice.core.dto.local.UserRegistrationDTO;
import by.itacademy.taskmanager.userservice.core.dto.local.VerificationParamsDTO;
import by.itacademy.taskmanager.userservice.core.enums.UserRole;
import by.itacademy.taskmanager.userservice.core.enums.UserStatus;
import by.itacademy.taskmanager.userservice.core.exceptions.custom.InactiveAccountException;
import by.itacademy.taskmanager.userservice.core.exceptions.custom.NoSuchUserException;
import by.itacademy.taskmanager.userservice.core.exceptions.custom.WrongPasswordException;
import by.itacademy.taskmanager.userservice.core.exceptions.custom.WrongVerificationCodeException;
import by.itacademy.taskmanager.userservice.dao.entity.Code;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import by.itacademy.taskmanager.userservice.endpoints.web.utils.JwtTokenHandler;
import by.itacademy.taskmanager.userservice.service.local.api.ICodeService;
import by.itacademy.taskmanager.userservice.service.local.api.IUserService;
import by.itacademy.taskmanager.userservice.service.audit.api.IAuditService;
import by.itacademy.taskmanager.userservice.service.local.api.IAuthenticationService;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor

@Service
public class AuthenticationService implements IAuthenticationService {

    private final IUserService userService;
    private final IAuditService auditService;

    private final IEmailSenderService emailSenderService;
    private final ICodeService codeService;

    private final ConversionService conversionService;
    private final Validator validator;

    private final UserDetailsService detailsService;
    private final UserHolder userHolder;
    private final JwtTokenHandler jwtTokenHandler;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User register(UserRegistrationDTO registrationDTO) {
        validate(registrationDTO);
        User createdUser;

        User user = conversionService.convert(registrationDTO, User.class);
        Objects.requireNonNull(user).setUuid(UUID.randomUUID());
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.WAITING_ACTIVATION);

        createdUser = userService.create(user);

        Code code = codeService.create(createdUser);

        try{
            emailSenderService.sendVerification(user.getEmail(), code.getCode().toString());
        } catch (MailException e){
            throw new RuntimeException(e);
        }

        try{
            auditService.send(createdUser, createdUser, "New user registered");
        } catch (FeignException e){
            throw new RuntimeException(e);
        }

        return createdUser;
    }

    @Override
    @Transactional
    public void verify(VerificationParamsDTO params) {
        User user = userService.getCardByEmail(params.getMail());

        List<Code> allForUser = codeService.getAllForUser(user);

        for (Code code : allForUser) {
            if (code.getCode().equals(params.getCode())){
                user.setStatus(UserStatus.ACTIVATED);
                userService.update(user);
                return;
            }
        }
        throw new WrongVerificationCodeException();
    }

    public String login(LoginDTO dto){

        UserDetails userDetails;

        try{
            userDetails = detailsService.loadUserByUsername(dto.getEmail());
        } catch (UsernameNotFoundException e){
            throw new NoSuchUserException();
        }

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
}
