package by.itacademy.taskmanager.audit_service.core.dto.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PageTaskFilterDto {

    private List<ProjectRefDto> project;

    private List<UserRefDto> implementer;

    private List<String> status;
}
