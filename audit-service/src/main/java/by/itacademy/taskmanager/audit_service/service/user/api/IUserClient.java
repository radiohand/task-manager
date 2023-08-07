package by.itacademy.taskmanager.audit_service.service.user.api;

import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "userClient")
public interface IUserClient {
    @RequestMapping(method = RequestMethod.GET, value = "{email}")
    UserDTO get(@PathVariable() String email);
}