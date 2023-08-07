package by.itacademy.taskmanager.audit_service.service.app.api;

import by.itacademy.taskmanager.audit_service.core.dto.app.AuditCreateDTO;
import by.itacademy.taskmanager.audit_service.dao.entity.Audit;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IAuditService {
    Audit create(AuditCreateDTO createDTO);
    Audit getCard(UUID uuid);
    Page<Audit> getPage(Integer page, Integer size);
}
