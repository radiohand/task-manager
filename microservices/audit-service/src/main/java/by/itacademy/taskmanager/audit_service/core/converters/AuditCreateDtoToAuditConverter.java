package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.local.AuditCreateDTO;
import by.itacademy.taskmanager.audit_service.core.enums.EssenceType;
import by.itacademy.taskmanager.audit_service.dao.entity.Audit;
import by.itacademy.taskmanager.audit_service.dao.entity.emb.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor

public class AuditCreateDtoToAuditConverter implements Converter<AuditCreateDTO, Audit> {

    private final ConversionService conversionService;
    @Override
    public Audit convert(AuditCreateDTO source) {

        Audit audit = new Audit();

        audit.setText(source.getText());
        audit.setEssenceType(EssenceType.valueOf(source.getEssenceType()));
        audit.setId(source.getId());
        audit.setUser(conversionService.convert(source.getUser(), User.class));

        return audit;
    }
}
