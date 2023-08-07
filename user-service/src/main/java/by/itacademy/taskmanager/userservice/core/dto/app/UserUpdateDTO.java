package by.itacademy.taskmanager.userservice.core.dto.app;

import by.itacademy.taskmanager.userservice.core.validators.annotations.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserUpdateDTO {
    @JsonProperty("mail")
    @NotBlank(message = "Value can't be blank")
    @Email(message = "Not valid format of email")
    private String email;

    @JsonProperty("fio")
    @NotBlank(message = "Value can't be blank")
    private String fio;

    @JsonProperty("status")
    @Pattern(regexp = "ACTIVATED|WAITING_ACTIVATION|DEACTIVATED", message = "Not valid value for user status")
    private String status;

    @JsonProperty("role")
    @Pattern(regexp = "USER|ADMIN", message = "Not valid value for user role")
    private String role;

    @JsonProperty("password")
    @NotBlank(message = "Value can't be blank")
    @Size(min = 6, message = "Too short value")
    @Size(max = 12, message = "Too long value")
    private String password;
}
