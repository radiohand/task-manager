package by.itacademy.taskmanager.audit_service.core.dto.app;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class UserRefDto {
    @NotBlank
    private UUID uuid;
}
