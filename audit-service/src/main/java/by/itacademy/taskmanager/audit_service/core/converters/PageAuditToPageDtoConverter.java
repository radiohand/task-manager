package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.app.AuditDTO;
import by.itacademy.taskmanager.audit_service.core.dto.app.PageAuditDTO;
import by.itacademy.taskmanager.audit_service.dao.entity.Audit;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

public class PageAuditToPageDtoConverter implements Converter<Page<Audit>, PageAuditDTO> {

    private final ConversionService conversionService;

    @Override
    public PageAuditDTO convert(Page<Audit> source) {

        PageAuditDTO pageDTO = new PageAuditDTO();
        List<AuditDTO> auditDTOS = new ArrayList<>();

        for (Audit audit : source.getContent()) {
            auditDTOS.add(conversionService.convert(audit, AuditDTO.class));
        }

        pageDTO.setNumber(source.getNumber());
        pageDTO.setSize(source.getSize());

        pageDTO.setTotalPages(source.getTotalPages());
        pageDTO.setTotalElements(source.getTotalElements());

        pageDTO.setFirst(source.isFirst());
        pageDTO.setNumberOfElements(source.getNumberOfElements());
        pageDTO.setLast(source.isLast());

        pageDTO.setContent(auditDTOS);

        return pageDTO;
    }
}
