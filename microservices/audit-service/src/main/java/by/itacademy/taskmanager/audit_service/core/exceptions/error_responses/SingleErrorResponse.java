package by.itacademy.taskmanager.audit_service.core.exceptions.error_responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SingleErrorResponse {
    @JsonProperty("logref")
    private final String logref = "error";
    @JsonProperty("message")
    private String message;
}
