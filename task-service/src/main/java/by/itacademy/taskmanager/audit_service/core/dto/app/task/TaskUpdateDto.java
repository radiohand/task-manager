package by.itacademy.taskmanager.audit_service.core.dto.app.task;

import by.itacademy.taskmanager.audit_service.core.dto.app.ProjectRefDto;
import by.itacademy.taskmanager.audit_service.core.dto.app.UserRefDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskUpdateDto {
    @JsonProperty("project")
    @NotBlank(message = "Value can't be blank")
    private ProjectRefDto project;

    @JsonProperty("title")
    @NotBlank(message = "Value can't be blank")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    @Pattern(regexp = "WAIT|BLOCK|IN_WORK|DONE|CLOSE", message = "Not valid value for task status")
    private String taskStatus;

    @JsonProperty("implementer")
    @NotBlank(message = "Value can't be blank")
    private UserRefDto implementer;
}
