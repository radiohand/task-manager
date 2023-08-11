package by.itacademy.taskmanager.audit_service.service.app;

import by.itacademy.taskmanager.audit_service.core.dto.app.AuditCreateDTO;
import by.itacademy.taskmanager.audit_service.core.dto.app.UserAuditDTO;
import by.itacademy.taskmanager.audit_service.core.enums.EssenceType;
import by.itacademy.taskmanager.audit_service.core.exceptions.NoSuchAuditException;
import by.itacademy.taskmanager.audit_service.dao.api.IAuditDao;
import by.itacademy.taskmanager.audit_service.dao.entity.Audit;
import by.itacademy.taskmanager.audit_service.dao.entity.emb.User;
import by.itacademy.taskmanager.audit_service.service.app.api.IAuditService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuditServiceTest {

    @Mock
    private IAuditDao auditDao;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private Validator validator;

    private IAuditService auditService;

    @BeforeEach
    public void before() {
        this.auditService = new AuditService(auditDao, conversionService, validator);
    }

    @Test
    public void createValidDto() {

        AuditCreateDTO dto = new AuditCreateDTO();
        dto.setUser(new UserAuditDTO());
        dto.setId("123");
        dto.setEssenceType("USER");
        dto.setText("something");

        Audit audit = new Audit();
        audit.setUser(new User());
        audit.setId("123");
        audit.setEssenceType(EssenceType.USER);
        audit.setText("something");

        auditService.create(dto);

        ArgumentCaptor<Audit> auditArgumentCaptor = ArgumentCaptor.forClass(Audit.class);

        BDDMockito.verify(auditDao).save(auditArgumentCaptor.capture());

        Audit capturedAudit = auditArgumentCaptor.getValue();

        audit.setUuid(capturedAudit.getUuid());

        assertThat(capturedAudit).isEqualTo(audit);
    }

    @Test
    public void createInvalidDto() {

        AuditCreateDTO dto = new AuditCreateDTO();
        dto.setUser(new UserAuditDTO());
        dto.setId("123");
        dto.setEssenceType("USER");
        dto.setText(null);

        assertThrows(ConstraintViolationException.class, ()-> auditService.create(dto));
    }

    @Test
    void testGetCardCorrectUuid() {
        Audit audit = new Audit();
        when(auditDao.findByUuid(any())).thenReturn(audit);
        assertSame(audit, auditService.getCard(UUID.randomUUID()));
        verify(auditDao).findByUuid(any());
    }

    @Test
    void testGetCardNonExistentUuid() {
        when(auditDao.findByUuid(any())).thenReturn(null);
        assertThrows(NoSuchAuditException.class, () -> auditService.getCard(UUID.randomUUID()));
        verify(auditDao).findByUuid(any());
    }

    @Test
    public void getSmallPage() {
        auditService.getPage(0, 1);
        BDDMockito.verify(auditDao).findAll(PageRequest.of(0, 1));
    }

    @Test
    public void getLargePage() {
        auditService.getPage(0, 3);
        BDDMockito.verify(auditDao).findAll(PageRequest.of(0, 3));
    }
}
