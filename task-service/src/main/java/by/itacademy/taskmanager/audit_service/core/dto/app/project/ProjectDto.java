package by.itacademy.taskmanager.audit_service.core.dto.app.project;

import by.itacademy.taskmanager.audit_service.core.dto.app.UserRefDto;
import by.itacademy.taskmanager.audit_service.core.enums.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProjectDto {
    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("dt_create")
    private Long dtCreate;

    @JsonProperty("dt_update")
    private Long dtUpdate;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("manager")
    private UserRefDto manager;

    @JsonProperty("staff")
    private List<UserRefDto> staff;

    @JsonProperty("status")
    private ProjectStatus projectStatus;
}
