package by.itacademy.taskmanager.audit_service.service.user.api;

import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(value = "user-service")
public interface IUserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/app/users/email/{email}")
    UserDTO get(@PathVariable() String email);

    @RequestMapping(method = RequestMethod.GET, value = "/app/users/uuid/{uuid}")
    UserDTO get(@PathVariable() UUID uuid);
}