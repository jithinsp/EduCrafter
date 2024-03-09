package com.corner.user.config.websocket;

import com.corner.user.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/application")
    @SendTo("/all/messages")
    public String send(String message) throws Exception{
        System.out.println(message);
//        System.out.println(message.getTo());
        return message;
    }

    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload String message){
        System.out.println("You are in private chat section");
//        simpMessagingTemplate.convertAndSendToUser(message.getPayload().toString(),"/specific",message);
    }
}
