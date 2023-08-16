package by.itacademy.taskmanager.audit_service.core.dto.local;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import by.itacademy.taskmanager.audit_service.core.enums.UserRole;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserAuditDTO {

    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("mail")
    private String email;
    @JsonProperty("fio")
    private String fio;

    @JsonProperty("role")
    private UserRole role;
}
