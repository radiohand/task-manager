package by.itacademy.taskmanager.audit_service.core.dto.app;

import com.fasterxml.jackson.annotation.JsonProperty;
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

public class ProjectRefDto {
    @JsonProperty("uuid")
    @NotBlank(message = "Value can't be blank")
    private UUID uuid;
}
