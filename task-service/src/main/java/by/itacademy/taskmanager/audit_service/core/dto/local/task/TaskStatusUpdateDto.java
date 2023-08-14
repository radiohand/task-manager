package by.itacademy.taskmanager.audit_service.core.dto.app.task;

import by.itacademy.taskmanager.audit_service.core.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskStatusUpdateDto {
    @Pattern(regexp = "WAIT|BLOCK|IN_WORK|DONE|CLOSE", message = "Not valid value for task status")
    @JsonProperty("status")
    private String taskStatus;
}
