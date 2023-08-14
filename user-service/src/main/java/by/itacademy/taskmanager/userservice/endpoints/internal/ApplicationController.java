package by.itacademy.taskmanager.userservice.endpoints.internal;

import by.itacademy.taskmanager.userservice.core.dto.local.*;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import by.itacademy.taskmanager.userservice.service.local.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor

@RestController
public class ApplicationController {

    private final IUserService service;
    private final ConversionService conversionService;

    @GetMapping(value = "/app/users/email/{email}")
    public ResponseEntity<UserDTO> getCardByEmail(@PathVariable String email){
        return ResponseEntity.ok(entityToDto(service.getCardByEmail(email)));
    }

    @GetMapping(value = "/app/users/uuid/{uuid}")
    public ResponseEntity<UserDTO> getCardByUuid(@PathVariable UUID uuid){
        return ResponseEntity.ok(entityToDto(service.getCard(uuid)));
    }

    private UserDTO entityToDto (User user){
        return conversionService.convert(user, UserDTO.class);
    }
}