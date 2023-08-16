package by.itacademy.taskmanager.audit_service.core.dto.local.task;

import by.itacademy.taskmanager.audit_service.core.dto.local.UserRefDto;
import by.itacademy.taskmanager.audit_service.core.dto.local.ProjectRefDto;
import by.itacademy.taskmanager.audit_service.core.enums.TaskStatus;
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

public class TaskDto {

    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("dt_create")
    private Long dtCreate;

    @JsonProperty("dt_update")
    private Long dtUpdate;

    @JsonProperty("project")
    private ProjectRefDto project;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private TaskStatus taskStatus;

    @JsonProperty("implementer")
    private UserRefDto implementer;
}
