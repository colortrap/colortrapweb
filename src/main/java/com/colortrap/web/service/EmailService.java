package com.colortrap.web.service;

import jakarta.mail.MessagingException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void sendReportEmail(String to, String reportEmail, String textTo,  String textReport) throws MessagingException {
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Успешно направена заявка за резервация.");
        message.setText(textTo);

        mailSender.send(message);

        SimpleMailMessage messageReport = new SimpleMailMessage();
        messageReport.setTo(reportEmail);
        messageReport.setSubject("Успешно направена заявка за резервация.");
        messageReport.setText(textReport);

        mailSender.send(messageReport);
    }
}
