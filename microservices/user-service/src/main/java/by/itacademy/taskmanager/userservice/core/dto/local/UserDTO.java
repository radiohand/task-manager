package by.itacademy.taskmanager.userservice.core.dto.local;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import by.itacademy.taskmanager.userservice.core.enums.UserRole;
import by.itacademy.taskmanager.userservice.core.enums.UserStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("dt_create")
    private Long dtCreate;
    @JsonProperty("dt_update")
    private Long dtUpdate;
    @JsonProperty("mail")
    private String email;
    @JsonProperty("fio")
    private String fio;

    @JsonProperty("status")
    private UserStatus status;
    @JsonProperty("role")
    private UserRole role;

//    private String position;
//    private Character[] password;
//    private String telegramNickName;
//    private NotificationMethod notificationMethod;
}
