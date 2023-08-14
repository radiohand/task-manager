package by.itacademy.taskmanager.audit_service.core.dto.local.task;

import by.itacademy.taskmanager.audit_service.core.dto.local.ProjectRefDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.UserRefDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Value can't be null")
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
    private UserRefDto implementer;
}
