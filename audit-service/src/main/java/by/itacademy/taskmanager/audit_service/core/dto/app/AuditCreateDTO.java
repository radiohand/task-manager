package by.itacademy.taskmanager.audit_service.core.dto.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuditCreateDTO {

    @JsonProperty("user")
    private UserAuditDTO user;

    @JsonProperty("text")
    private String text;

    @JsonProperty("essence_type")
    private String essenceType;

    @JsonProperty("id")
    private String id;
}