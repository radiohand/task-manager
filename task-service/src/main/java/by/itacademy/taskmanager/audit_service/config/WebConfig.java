package by.itacademy.taskmanager.audit_service.config;

import by.itacademy.taskmanager.audit_service.core.converters.*;
import by.itacademy.taskmanager.audit_service.dao.api.IProjectDao;
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

    private IProjectDao projectDao;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {

        ConversionService conversionService = (ConversionService) registry;

        registry.addConverter(new EpochToLocalDateTimeConverter());
        registry.addConverter(new LocalDateTimeToEpochConverter());
        registry.addFormatter(new LocalDateTimeFormatter(conversionService));

        registry.addConverter(new ProjectCreateDtoToProjectConverter(conversionService));
        registry.addConverter(new ProjectUpdateDtoToProjectConverter(conversionService));
        registry.addConverter(new ProjectToProjectDtoConverter(conversionService));

        registry.addConverter(new TaskCreateDtoToTaskConverter(projectDao,conversionService));
        registry.addConverter(new TaskUpdateDtoToTaskConverter(projectDao,conversionService));
        registry.addConverter(new TaskToTaskDtoConverter(conversionService));

        registry.addConverter(new PageProjectToPageDtoConverter(conversionService));
        registry.addConverter(new PageTaskToPageDtoConverter(conversionService));

        registry.addConverter(new UserToUserRefDtoConverter());
        registry.addConverter(new UserRefDtoToUserConverter());
    }
}
