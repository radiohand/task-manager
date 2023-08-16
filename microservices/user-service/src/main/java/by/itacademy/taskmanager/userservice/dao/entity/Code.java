package by.itacademy.taskmanager.userservice.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "code")
public class Code implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID uuid;

    private Integer code;

    @ManyToOne
    private User user;
}