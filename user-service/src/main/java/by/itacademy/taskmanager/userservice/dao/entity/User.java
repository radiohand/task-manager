package by.itacademy.taskmanager.userservice.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import by.itacademy.taskmanager.userservice.core.enums.UserRole;
import by.itacademy.taskmanager.userservice.core.enums.UserStatus;

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
@Table (name = "user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 3L;

    @Id
    private UUID uuid;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    private String fio;
//    private String position;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String email;

    private Integer verifyCode;
//    private String telegramNickName;
//    @Enumerated(EnumType.STRING)
//    private NotificationMethod notificationMethod;
}