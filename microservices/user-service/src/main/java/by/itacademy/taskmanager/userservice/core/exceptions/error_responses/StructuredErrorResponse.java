package by.itacademy.taskmanager.userservice.core.exceptions.error_responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class StructuredErrorResponse {
    @JsonProperty("logref")
    private final String logref = "structured_error";
    @JsonProperty("errors")
    private List<NestedErrorResponse> errors;
}
