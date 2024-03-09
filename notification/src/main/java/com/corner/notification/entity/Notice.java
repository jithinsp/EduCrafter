package com.corner.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notice {
//    @Id
//    private UUID id;
    @Id
    private String messageId;
    private String message;
    private String description;
    private Date messageDate;
    private boolean isEnabled=true;
}
