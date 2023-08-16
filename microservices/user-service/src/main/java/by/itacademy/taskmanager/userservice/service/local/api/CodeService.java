package by.itacademy.taskmanager.userservice.service.local.api;

import by.itacademy.taskmanager.userservice.dao.api.ICodeDao;
import by.itacademy.taskmanager.userservice.dao.entity.Code;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@AllArgsConstructor

@Service
public class CodeService implements ICodeService{

    private static final int MIN_CODE_VALUE = 100_000;
    private static final int MAX_CODE_VALUE = 999_999;

    private final Random random = new Random();

    private ICodeDao dao;

    @Override
    public Code create(User user) {
        Integer verifyCode = generateCode();
        Code code = new Code(UUID.randomUUID(), verifyCode, user);
        return dao.saveAndFlush(code);
    }

    @Override
    public List<Code> getAllForUser(User user) {
        return dao.findAllByUser(user);
    }

    private Integer generateCode(){
        return random.nextInt(MAX_CODE_VALUE - MIN_CODE_VALUE) + MIN_CODE_VALUE;
    }
}
