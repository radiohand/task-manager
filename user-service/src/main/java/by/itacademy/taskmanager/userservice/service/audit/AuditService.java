package by.itacademy.taskmanager.userservice.service.audit;

import by.itacademy.taskmanager.userservice.core.dto.audit.AuditDTO;
import by.itacademy.taskmanager.userservice.core.dto.audit.UserAuditDTO;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import by.itacademy.taskmanager.userservice.service.audit.api.IAuditSenderService;
import by.itacademy.taskmanager.userservice.service.audit.api.IAuditService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class AuditService implements IAuditService {

    private static final String ESSENCE_TYPE = "USER";

    private final IAuditSenderService auditSenderService;

    @Override
    public void send(User target, User performer, String text) {
        send(generate(target, performer, text));
    }

    private AuditDTO generate(User target, User performer, String text){
        AuditDTO dto = new AuditDTO();
        UserAuditDTO userAuditDTO = new UserAuditDTO();
        userAuditDTO.setUuid(performer.getUuid());
        userAuditDTO.setFio(performer.getFio());
        userAuditDTO.setEmail(performer.getEmail());
        userAuditDTO.setRole(performer.getRole());

        dto.setUser(userAuditDTO);
        dto.setId(target.getUuid().toString());
        dto.setText(text);
        dto.setEssenceType(ESSENCE_TYPE);

        return dto;
    }

    private void send(AuditDTO dto){
        auditSenderService.send(dto);
    }
}
