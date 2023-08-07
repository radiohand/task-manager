package by.itacademy.taskmanager.userservice.endpoints.app.controllers;

import by.itacademy.taskmanager.userservice.core.dto.app.*;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import by.itacademy.taskmanager.userservice.service.app.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
public class ApplicationController {

    private final IUserService service;
    private final ConversionService conversionService;

    @GetMapping(value = "/app/users/{email}")
    public ResponseEntity<UserDTO> getCard(@PathVariable String email){
        return ResponseEntity.ok(entityToDto(service.getCardByEmail(email)));
    }

    private UserDTO entityToDto (User user){
        return conversionService.convert(user, UserDTO.class);
    }
}