package com.corner.user.service.email;

public interface EmailService {
    public void sendMail(String toEmail,
                         String subject,
                         String body);
}
