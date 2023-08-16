package by.itacademy.taskmanager.userservice;

import by.itacademy.taskmanager.userservice.config.property.AppProperty;
import by.itacademy.taskmanager.userservice.core.enums.UserRole;
import by.itacademy.taskmanager.userservice.core.enums.UserStatus;
import by.itacademy.taskmanager.userservice.dao.api.IUserDao;
import by.itacademy.taskmanager.userservice.dao.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.UUID;

@EnableWebMvc
@EnableJpaRepositories
@EnableTransactionManagement
@EnableConfigurationProperties(AppProperty.class)
@EnableFeignClients
@EnableWebSecurity

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(IUserDao userDao, PasswordEncoder passwordEncoder){
		return args -> {
			if (userDao.findByEmail("admin@gmail.com") == null){
				User admin = new User();
				admin.setUuid(UUID.randomUUID());
				admin.setEmail("admin@gmail.com");
				admin.setPassword(passwordEncoder.encode("12345678"));
				admin.setFio("admin");
				admin.setRole(UserRole.ADMIN);
				admin.setStatus(UserStatus.ACTIVATED);

				userDao.save(admin);
			}
		};
	}
}
