package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.local.UserAuditDTO;
import by.itacademy.taskmanager.audit_service.dao.entity.emb.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor

public class UserToUserAuditDtoConverter implements Converter<User, UserAuditDTO> {

    @Override
    public UserAuditDTO convert(User source) {
        UserAuditDTO userAuditDTO = new UserAuditDTO();

        userAuditDTO.setEmail(source.getEmail());
        userAuditDTO.setUuid(source.getUuid());
        userAuditDTO.setFio(source.getFio());
        userAuditDTO.setRole(source.getRole());

        return userAuditDTO;
    }
}
