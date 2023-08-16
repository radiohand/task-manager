package by.itacademy.taskmanager.audit_service.core.dto.user;

import by.itacademy.taskmanager.audit_service.core.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("dt_create")
    private Long dtCreate;
    @JsonProperty("dt_update")
    private Long dtUpdate;
    @JsonProperty("mail")
    private String email;
    @JsonProperty("fio")
    private String fio;

    @JsonProperty("status")
    private String status;

    @JsonProperty("role")
    private UserRole role;
}
