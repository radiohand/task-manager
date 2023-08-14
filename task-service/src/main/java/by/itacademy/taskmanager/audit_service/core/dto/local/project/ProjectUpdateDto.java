package by.itacademy.taskmanager.audit_service.core.dto.app.project;

import by.itacademy.taskmanager.audit_service.core.dto.app.UserRefDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

public class ProjectUpdateDto {

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

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Value can't be null")
    @Pattern(regexp = "ACTIVE|ARCHIVED", message = "Not valid value for task status")
    @JsonProperty("status")
    private String projectStatus;
}
