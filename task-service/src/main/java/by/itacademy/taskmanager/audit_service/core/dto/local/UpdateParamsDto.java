package by.itacademy.taskmanager.audit_service.core.dto.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UpdateParamsDto {

    private UUID uuid;

    @PastOrPresent(message = "value must be past or present")
    private LocalDateTime version;
}
