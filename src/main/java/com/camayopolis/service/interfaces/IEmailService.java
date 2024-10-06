package com.camayopolis.service.interfaces;

import jakarta.mail.MessagingException;

public interface IEmailService {
    void sendEmail(String to, String subject, String content) throws MessagingException;
    void sendConfirmationEmail(String to, String token) throws MessagingException;
}
