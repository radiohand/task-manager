package by.itacademy.taskmanager.audit_service.service.app;

import by.itacademy.taskmanager.audit_service.core.dto.app.AuditCreateDTO;
import by.itacademy.taskmanager.audit_service.core.exceptions.NoSuchAuditException;
import by.itacademy.taskmanager.audit_service.dao.entity.Audit;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import by.itacademy.taskmanager.audit_service.dao.api.IAuditDao;
import by.itacademy.taskmanager.audit_service.service.app.api.IAuditService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AuditService implements IAuditService {

    private final IAuditDao dao;

    private final ConversionService conversionService;
    private final Validator validator;

    @Override
    @Transactional
    public Audit create(AuditCreateDTO createDTO) {
        validate(createDTO);
        by.itacademy.taskmanager.audit_service.dao.entity.Audit audit = conversionService.convert(createDTO, by.itacademy.taskmanager.audit_service.dao.entity.Audit.class);
        Objects.requireNonNull(audit).setUuid(UUID.randomUUID());
        return create(audit);
    }

    @Transactional(readOnly = true)
    @Override
    public Audit getCard(UUID uuid) {
        Audit audit = dao.findByUuid(uuid);
        if (audit == null){
            throw new NoSuchAuditException();
        }
        return audit;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Audit> getPage(Integer page, Integer size) {
        return dao.findAll(PageRequest.of(Math.abs(page), Math.abs(size)));
    }

    @Transactional
    protected Audit create(Audit audit) {
        return dao.save(audit);
    }
    private <T> void validate(T dto){
        Set<ConstraintViolation<T>> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}