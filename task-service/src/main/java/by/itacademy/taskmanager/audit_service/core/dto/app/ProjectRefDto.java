package by.itacademy.taskmanager.audit_service.core.dto.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class ProjectRefDto {
    @JsonProperty("uuid")
    @NotBlank(message = "Value can't be blank")
    private UUID uuid;
}
