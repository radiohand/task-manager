package by.itacademy.taskmanager.audit_service.dao.api;

import by.itacademy.taskmanager.audit_service.core.enums.TaskStatus;
import by.itacademy.taskmanager.audit_service.dao.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ITaskDao extends JpaRepository<Task, Long> {
    Task findByUuid (UUID uuid);

    @Modifying
    @Query(value = "SELECT * FROM app.task " +
            "WHERE task.project_uuid = " +
            "(SELECT uuid FROM app.project " +
            "WHERE manager_uuid = ?1 " +
            "OR (SELECT staff_uuid FROM app.project_staff WHERE project_uuid = app.project.uuid) = ?1)",
    nativeQuery = true)
    List<Task> findAllForUser(UUID userUuid);

    @Modifying
    @Query(value = "UPDATE app.task SET task_status = ?2 WHERE uuid = ?1", nativeQuery = true)
    Task updateStatus(@Param(value = "uuid") UUID uuid, @Param(value = "task_status") TaskStatus status);
}