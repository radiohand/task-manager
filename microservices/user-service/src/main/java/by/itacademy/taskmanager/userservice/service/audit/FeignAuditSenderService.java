package by.itacademy.taskmanager.userservice.service.audit;

import by.itacademy.taskmanager.userservice.core.dto.audit.AuditDTO;
import by.itacademy.taskmanager.userservice.service.audit.api.IAuditClient;
import by.itacademy.taskmanager.userservice.service.audit.api.IAuditSenderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class FeignAuditSenderService implements IAuditSenderService {

    private final IAuditClient client;

    @Override
    public void send(AuditDTO dto) {
        client.send(dto);
    }
}
