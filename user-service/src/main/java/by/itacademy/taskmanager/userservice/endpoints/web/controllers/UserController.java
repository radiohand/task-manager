package by.itacademy.taskmanager.userservice.endpoints.web.controllers;

import by.itacademy.taskmanager.userservice.core.dto.app.*;
import lombok.AllArgsConstructor;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import by.itacademy.taskmanager.userservice.service.app.api.IUserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor

@RestController
public class UserController {

    private final IUserService service;
    private final ConversionService conversionService;

    @GetMapping("/users")
    public ResponseEntity<PageDTO<UserDTO>> getPage(@RequestParam (defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "20") Integer size) {
        return ResponseEntity.ok(pageToPageDTO(service.getPage(new PageGetterParamsDTO(page, size))));
    }

    @GetMapping(value = "/users/{uuid}")
    public ResponseEntity<UserDTO> getCard(@PathVariable UUID uuid){
        return ResponseEntity.ok(entityToDto(service.getCard(uuid)));
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO createDTO){
        return new ResponseEntity<>(entityToDto(service.create(createDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/users/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<UserDTO> update(@PathVariable UUID uuid,
                          @PathVariable("dt_update") Long version,
                          @RequestBody UserUpdateDTO updateDTO){
        LocalDateTime dateTimeVersion = conversionService.convert(version, LocalDateTime.class);
        return ResponseEntity.ok(entityToDto(service.update(new UpdateParamsDTO(uuid, dateTimeVersion), updateDTO)));

    }

    private UserDTO entityToDto(User user){
        return conversionService.convert(user, UserDTO.class);
    }

    private UserPageDTO pageToPageDTO (Page<User> page){
        return conversionService.convert(page, UserPageDTO.class);
    }
}