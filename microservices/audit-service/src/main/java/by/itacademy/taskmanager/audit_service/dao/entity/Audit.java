package by.itacademy.taskmanager.audit_service.dao.entity;

import by.itacademy.taskmanager.audit_service.core.enums.EssenceType;
import by.itacademy.taskmanager.audit_service.dao.entity.emb.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
@Table (name = "audit")
public class Audit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID uuid;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    private String text;

    @Enumerated(EnumType.STRING)
    private EssenceType essenceType;

    private String id;

    @Embedded
    private User user;
}