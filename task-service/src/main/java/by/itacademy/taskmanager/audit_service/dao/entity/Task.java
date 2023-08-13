package by.itacademy.taskmanager.audit_service.dao.entity;

import by.itacademy.taskmanager.audit_service.core.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table (name = "task")
public class Task implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID uuid;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    @ManyToOne
    private Project project;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne
    private User implementer;
}