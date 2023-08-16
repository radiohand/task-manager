package by.itacademy.taskmanager.audit_service.core.exceptions.error_responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class NestedErrorResponse {
    @JsonProperty("message")
    private final String message;
    @JsonProperty("field")
    private final String field;
}
