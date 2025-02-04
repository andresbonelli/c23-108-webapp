package tech.nocountry.roadbites.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender javaMailSender;

    public void sendOrderConfirmationEmail(String to, String body) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setFrom("noreply@andresbonelli.dev");
            helper.setSubject("RoadBites - Pedido Confirmado");
            helper.setText(body, true);

            javaMailSender.send(message);

            log.info("Email successfully sent to: {}", to);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
