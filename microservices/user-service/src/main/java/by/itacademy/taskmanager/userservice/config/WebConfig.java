package by.itacademy.taskmanager.userservice.config;

import by.itacademy.taskmanager.userservice.core.converters.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AllArgsConstructor

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

        ConversionService conversionService = (ConversionService) registry;

        registry.addConverter(new UserToUserDtoConverter(conversionService));
        registry.addConverter(new UserUpdateDtoToUserConverter());

        registry.addConverter(new EpochToLocalDateTimeConverter());
        registry.addConverter(new LocalDateTimeToEpochConverter());
        registry.addFormatter(new LocalDateTimeFormatter(conversionService));

        registry.addConverter(new UserCreateDtoToUserConverter(passwordEncoder()));
        registry.addConverter(new UserRegistrationDtoToUserConverter(passwordEncoder()));

        registry.addConverter(new PageToUserPageDtoConverter(conversionService));
    }
}
