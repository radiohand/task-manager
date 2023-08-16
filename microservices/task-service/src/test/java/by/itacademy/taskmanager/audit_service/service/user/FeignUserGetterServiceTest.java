package by.itacademy.taskmanager.audit_service.service.user;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.itacademy.taskmanager.audit_service.core.dto.user.UserDTO;
import by.itacademy.taskmanager.audit_service.service.user.api.IUserClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FeignUserGetterService.class})
@ExtendWith(SpringExtension.class)
class FeignUserGetterServiceTest {
    @Autowired
    private FeignUserGetterService feignUserGetterService;

    @MockBean
    private IUserClient iUserClient;

    /**
     * Method under test: {@link FeignUserGetterService#get(String)}
     */
    @Test
    void testGet() {
        UserDTO userDTO = new UserDTO();
        when(iUserClient.get((String) any())).thenReturn(userDTO);
        assertSame(userDTO, feignUserGetterService.get("jane.doe@example.org"));
        verify(iUserClient).get((String) any());
    }
}

