package by.itacademy.taskmanager.userservice.service.email;

import by.itacademy.taskmanager.userservice.config.property.AppProperty;
import by.itacademy.taskmanager.userservice.service.email.api.IEmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GmailSenderService implements IEmailSenderService {

    private final AppProperty property;
    private final JavaMailSender mailSender;

    @Override
    public void sendVerification(String userAddress,
                                 String verificationCode){

        sendEmail(userAddress, generateMessage(userAddress, verificationCode));
    }

    private String generateMessage(String userAddress,
                                   String verificationCode){
        return property.getEmailServiceProp().getRedirectUrl() +
                "?" +
                "code=" +
                verificationCode +
                "&" +
                "mail=" +
                userAddress;
    }

    private void sendEmail(String userAddress,
                           String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userAddress);
        message.setText(body);
        message.setSubject(property.getEmailServiceProp().getEmailSubject());

        mailSender.send(message);
    }
}
