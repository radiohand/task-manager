package by.itacademy.taskmanager.audit_service.core.dto.app;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRefDto {
    @NotBlank
    private UUID uuid;
}
