package by.itacademy.taskmanager.userservice.endpoints.web.controllers;

import by.itacademy.taskmanager.userservice.core.dto.app.*;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import by.itacademy.taskmanager.userservice.service.app.api.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
public class AuthenticationController {

    private final IAuthenticationService service;
    private final ConversionService conversionService;

    @GetMapping(value = "/users/verification")
    public void verify(@RequestParam Integer code,
                       @RequestParam String mail){
        service.verify(new VerificationParamsDTO(code, mail));
    }

    @PostMapping(value = "/users/registration")
    public ResponseEntity<UserDTO> register(@RequestBody UserRegistrationDTO registrationDTO){
        return new ResponseEntity<>(entityToDto(service.register(registrationDTO)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/users/login")
    public String login(@RequestBody LoginDTO loginDTO){
        return service.login(loginDTO);
    }

    @GetMapping(value = "/users/me")
    public UserDTO getMe(){
        return entityToDto(service.getMe());
    }

    private UserDTO entityToDto(User user){
        return conversionService.convert(user, UserDTO.class);
    }
}
