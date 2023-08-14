package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.local.AuditDTO;
import by.itacademy.taskmanager.audit_service.core.dto.local.UserAuditDTO;
import by.itacademy.taskmanager.audit_service.dao.entity.Audit;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RequiredArgsConstructor

public class AuditToAuditDtoConverter implements Converter<Audit, AuditDTO> {

    private final ConversionService conversionService;

    @Override
    public AuditDTO convert(Audit source) {

        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setUuid(source.getUuid());
        auditDTO.setDtCreate(ZonedDateTime.of(source.getDtCreate(), ZoneOffset.UTC).toInstant().toEpochMilli());
        auditDTO.setText(source.getText());
        auditDTO.setEssenceType(source.getEssenceType());
        auditDTO.setId(source.getId());
        auditDTO.setUser(conversionService.convert(source.getUser(), UserAuditDTO.class));

        return auditDTO;
    }
}
