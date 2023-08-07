package by.itacademy.taskmanager.userservice.core.dto.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LoginDTO {
    @JsonProperty("mail")
    private String email;
    @JsonProperty("password")
    private String password;
}
