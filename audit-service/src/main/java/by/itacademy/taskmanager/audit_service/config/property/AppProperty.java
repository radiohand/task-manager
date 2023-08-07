package by.itacademy.taskmanager.audit_service.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
@Getter
@Setter
public class AppProperty {

    public JwtProp jwtProp;

    @Getter
    @Setter
    public static class JwtProp{
        private String secret;
        private String issuer;
    }
}
