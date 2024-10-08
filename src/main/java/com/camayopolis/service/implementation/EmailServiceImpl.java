package com.camayopolis.service.implementation;

import com.camayopolis.service.interfaces.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService{

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    @Override
    public void sendEmail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public void sendConfirmationEmail(String to, String token) throws MessagingException {
        String confirmationLink = "http://localhost:8080/auth/confirm?token=" + token;
        String message = "<p>Gracias por registrarte. Haz clic en el siguiente enlace para confirmar tu cuenta:</p>"
                + "<a href=\"" + confirmationLink + "\">Confirmar cuenta</a>";

        sendEmail(to, "Confirmación de registro", message);
    }
}
