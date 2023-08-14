package by.itacademy.taskmanager.userservice.service.audit.api;

import by.itacademy.taskmanager.userservice.core.dto.audit.AuditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "auditClient")
public interface IAuditClient {
    @RequestMapping(method = RequestMethod.POST)
    void send(AuditDTO dto);
}
