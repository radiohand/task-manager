package by.itacademy.taskmanager.audit_service.dao.api;

import by.itacademy.taskmanager.audit_service.dao.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IProjectDao extends JpaRepository<Project, UUID> {
    Project findByUuid (UUID uuid);

    @Modifying
    @Query(value = "SELECT * FROM app.project WHERE manager_uuid = ?1 OR ?1 IN (SELECT staff_uuid FROM app.project_staff WHERE project_uuid = app.project.uuid)", nativeQuery = true)
    List<Project> findAllForUser(UUID userUuid);
}