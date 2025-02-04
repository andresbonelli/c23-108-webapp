package tech.nocountry.roadbites.config;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import tech.nocountry.roadbites.service.EmailService;

@TestConfiguration
public class TestConfig {

    @Bean(name = "testJavaMailSender")
    public JavaMailSender testJavaMailSender() {
        return Mockito.mock(JavaMailSender.class); // Mock the email sender
    }

    @Bean
    public EmailService emailService(@Qualifier("testJavaMailSender") JavaMailSender javaMailSender) {
        EmailService mockEmailService = Mockito.mock(EmailService.class);
        Mockito
                .doNothing()
                .when(mockEmailService)
                .sendOrderConfirmationEmail(
                        Mockito.anyString(),
                        Mockito.anyString()
                );
        return mockEmailService;
    }
}
