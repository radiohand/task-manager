package by.itacademy.taskmanager.audit_service.core.dto.local;

import by.itacademy.taskmanager.audit_service.core.enums.EssenceType;
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

public class AuditDTO {

    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("dt_create")
    private Long dtCreate;

    @JsonProperty("user")
    private UserAuditDTO user;

    @JsonProperty("text")
    private String text;

    @JsonProperty("essence_type")
    private EssenceType essenceType;

    @JsonProperty("id")
    private String id;
}
