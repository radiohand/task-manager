package by.itacademy.taskmanager.userservice.service.audit.api;

import by.itacademy.taskmanager.userservice.core.dto.audit.AuditDTO;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "audit-service")
public interface IAuditClient {
    @RequestMapping(method = RequestMethod.POST, value = "/app/audit")
    void send(AuditDTO dto);
}
