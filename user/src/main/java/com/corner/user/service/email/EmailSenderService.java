package com.corner.user.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailService{
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String toEmail,
                         String subject,
                         String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jithinsp4@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

    }
}
