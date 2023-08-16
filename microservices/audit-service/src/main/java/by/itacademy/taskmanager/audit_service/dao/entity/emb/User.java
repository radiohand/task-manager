package by.itacademy.taskmanager.audit_service.dao.entity.emb;

import jakarta.persistence.*;
import lombok.*;
import by.itacademy.taskmanager.audit_service.core.enums.UserRole;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Embeddable
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "user_uuid")
    private UUID uuid;

    @Column(name = "user_fio")
    private String fio;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "user_email")
    private String email;
}