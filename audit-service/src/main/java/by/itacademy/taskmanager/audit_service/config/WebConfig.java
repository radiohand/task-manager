package by.itacademy.taskmanager.audit_service.config;

import by.itacademy.taskmanager.audit_service.core.converters.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new UserToUserAuditDtoConverter());
        registry.addConverter(new UserAuditDtoToUserConverter());

        ConversionService conversionService = (ConversionService) registry;

        registry.addConverter(new AuditCreateDtoToAuditConverter(conversionService));
        registry.addConverter(new AuditToAuditDtoConverter(conversionService));

        registry.addConverter(new PageAuditToPageDtoConverter(conversionService));
    }
}
