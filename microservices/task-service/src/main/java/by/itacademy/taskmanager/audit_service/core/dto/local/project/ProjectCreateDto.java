package by.itacademy.taskmanager.audit_service.core.dto.local.project;

import by.itacademy.taskmanager.audit_service.core.dto.local.UserRefDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProjectCreateDto {

    @JsonProperty("name")
    @NotBlank(message = "Value can't be blank")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("manager")
    @NotNull(message = "Value can't be null")
    private UserRefDto manager;

    @JsonProperty("staff")
    @NotNull(message = "Value can't be null")
    private List<UserRefDto> staff;

    @JsonProperty("status")
    @Pattern(regexp = "ACTIVE|ARCHIVED", message = "Not valid value for project status")
    private String projectStatus;
}
