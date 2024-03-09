package com.corner.notification.service;

import com.corner.notification.dto.CustomMessage;
import com.corner.notification.entity.Notice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NoticeService {
    Notice saveMessage(CustomMessage message);

    List<Notice> getAll();

    Notice getById(String noticeId);
}
