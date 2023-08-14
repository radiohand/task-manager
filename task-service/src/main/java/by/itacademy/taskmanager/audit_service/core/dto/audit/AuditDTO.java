package by.itacademy.taskmanager.audit_service.core.dto.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AuditDTO {

    @JsonProperty("user")
    private UserAuditDTO user;

    @JsonProperty("text")
    private String text;

    @JsonProperty("essence_type")
    private String essenceType;

    @JsonProperty("id")
    private String id;
}
