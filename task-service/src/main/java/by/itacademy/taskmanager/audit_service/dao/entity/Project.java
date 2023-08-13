package by.itacademy.taskmanager.audit_service.dao.entity;

import by.itacademy.taskmanager.audit_service.core.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table (name = "project")
public class Project implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID uuid;

    @CreationTimestamp
    @Column(name = "dt_create", updatable = false)
    private LocalDateTime dtCreate;

    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    private String name;

    private String description;

    @ManyToOne
    private User manager;

    @ManyToMany
    private List<User> staff;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;
}