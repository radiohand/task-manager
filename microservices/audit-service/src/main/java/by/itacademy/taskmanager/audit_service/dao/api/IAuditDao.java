package by.itacademy.taskmanager.audit_service.dao.api;

import by.itacademy.taskmanager.audit_service.dao.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAuditDao extends JpaRepository<Audit, UUID> {
    Audit findByUuid (UUID uuid);
}