package tech.nocountry.roadbites.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import tech.nocountry.roadbites.service.EmailService;

@TestConfiguration
public class TestConfig {

    @Bean
    public JavaMailSender mockJavaMailSender() {
        return Mockito.mock(JavaMailSender.class); // Mock the email sender
    }

    @Bean
    public EmailService emailService() {
        return new EmailService(mockJavaMailSender()) {
            @Override
            public void sendEmail(String to, String subject, String body) {
                System.out.println("Email sent to: " + to);
            }
        };
    }
}
