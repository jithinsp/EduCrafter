package com.corner.notification.controller;

import com.corner.notification.config.MQConfig;
import com.corner.notification.dto.CustomMessage;
import com.corner.notification.entity.Notice;
import com.corner.notification.service.NoticeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("notification")
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;
    @Autowired
    NoticeService noticeService;

    @PostMapping("/publish")
    public ResponseEntity<?> publishMessage(@RequestBody CustomMessage message) {
        try {
            message.setMessageId(UUID.randomUUID().toString());
            message.setMessageDate(new Date());
            System.out.println(message.getMessage());
            template.convertAndSend(MQConfig.EXCHANGE,
                    MQConfig.ROUTING_KEY, message);
            Notice notice = noticeService.saveMessage(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(notice);
//        } catch (DataIntegrityViolationException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (JsonProcessingException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to convert message to JSON");
        } catch (AmqpException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to publish message to the message broker");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Messaging operation failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed: " + e.getMessage());
        }
    }

    @GetMapping("getAll")
    public List<Notice> getAll(){
        List<Notice> notices = noticeService.getAll();
        System.out.println(notices);
        return notices;
    }

    @GetMapping("get/{noticeId}")
    public Notice getById(@PathVariable String noticeId){
        return noticeService.getById(noticeId);
    }
}