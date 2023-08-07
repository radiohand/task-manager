package by.itacademy.taskmanager.audit_service.service.security;

import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;
import by.itacademy.taskmanager.audit_service.core.dto.user.UserDetailsDto;
import by.itacademy.taskmanager.audit_service.service.user.api.IUserGetterService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class FeignUserDetailsService implements UserDetailsService {

    private static final String USER_STATUS_ACTIVATED = "ACTIVATED";

    private final IUserGetterService userGetterService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO user = userGetterService.get(email);
        return UserDetailsDto.builder()
                .username(user.getEmail())
                .password("")
                .roles(user.getRole())
                .accountLocked(!user.getStatus().equals(USER_STATUS_ACTIVATED))
                .build();
    }
}
