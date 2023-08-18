package by.itacademy.taskmanager.userservice.service.audit;

import by.itacademy.taskmanager.userservice.core.dto.audit.AuditDTO;
import by.itacademy.taskmanager.userservice.service.audit.api.IAuditSenderService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
@Primary
public class KafkaAuditSenderService implements IAuditSenderService {

    private final KafkaTemplate<String, AuditDTO> kafkaTemplate;

    @Override
    public void send(AuditDTO dto) {
        kafkaTemplate.send("auditTopic", dto);
    }
}