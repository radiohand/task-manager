package by.itacademy.taskmanager.audit_service.core.converters;

import by.itacademy.taskmanager.audit_service.core.dto.app.UserAuditDTO;
import by.itacademy.taskmanager.audit_service.dao.entity.emb.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor

public class UserAuditDtoToUserConverter implements Converter<UserAuditDTO, User> {

    @Override
    public User convert(UserAuditDTO source) {
        User user = new User();

        user.setEmail(source.getEmail());
        user.setUuid(source.getUuid());
        user.setFio(source.getFio());
        user.setRole(source.getRole());

        return user;
    }
}
