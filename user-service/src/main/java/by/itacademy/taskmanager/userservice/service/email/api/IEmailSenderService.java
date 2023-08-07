package by.itacademy.taskmanager.userservice.service.email.api;

public interface IEmailSenderService {
    void sendVerification(String userAddress, String verificationCode);
}
