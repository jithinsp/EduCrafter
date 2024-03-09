package com.corner.user.config.rabbitmq;

import com.corner.user.service.email.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @Autowired
    EmailService emailService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    public static final String EMAIL = "jithinsp001@gmail.com";

    @RabbitListener(queues = MQConfig.QUEUE)
//    @MessageMapping("/application")
//    @SendTo("/all/messages")
    public String listener(CustomMessage message){
//        messagingTemplate.convertAndSend("/topic/notification", message);
        System.out.println(message);
        return message.getMessage();
//        sendMail(EMAIL, message.getMessage());
    }

    public void sendMail(String email, String message){
        emailService.sendMail(email,
                "Notification",
                message);
    }
}