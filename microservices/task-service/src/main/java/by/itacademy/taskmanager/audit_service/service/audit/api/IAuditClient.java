package by.itacademy.taskmanager.audit_service.service.audit.api;

import by.itacademy.taskmanager.audit_service.core.dto.audit.AuditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "audit-service")
public interface IAuditClient {
    @RequestMapping(method = RequestMethod.POST, value = "/app/audit")
    void send(AuditDTO dto);
}
