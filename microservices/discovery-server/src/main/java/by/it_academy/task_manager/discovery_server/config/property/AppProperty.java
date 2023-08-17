package by.it_academy.task_manager.discovery_server.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
@Getter
@Setter
public class AppProperty {

    public SecurityProp securityProp;

    @Getter
    @Setter
    public static class SecurityProp{
        private String username;
        private String password;
    }
}
