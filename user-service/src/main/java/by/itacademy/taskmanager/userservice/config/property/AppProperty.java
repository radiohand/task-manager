package by.itacademy.taskmanager.userservice.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
@Getter
@Setter
public class AppProperty {

    public EmailServiceProp emailServiceProp;

    public JwtProp jwtProp;

    @Getter
    @Setter
    public static class JwtProp{
        private String secret;
        private String issuer;
    }

    @Getter
    @Setter
    public static class EmailServiceProp{
        private String redirectUrl;
        private String emailSubject;
    }
}
