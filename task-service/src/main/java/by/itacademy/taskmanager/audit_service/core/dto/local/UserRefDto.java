package by.itacademy.taskmanager.audit_service.core.dto.local;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class UserRefDto {
    @JsonProperty("uuid")
    private UUID uuid;
}
