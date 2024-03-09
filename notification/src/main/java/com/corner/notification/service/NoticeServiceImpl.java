package com.corner.notification.service;

import com.corner.notification.dto.CustomMessage;
import com.corner.notification.entity.Notice;
import com.corner.notification.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    NoticeRepository noticeRepository;

    public Notice saveMessage(CustomMessage message) {
        Notice notice = new Notice();
        notice.setMessage(message.getMessage());
        notice.setMessageId(message.getMessageId());
        notice.setMessageDate(message.getMessageDate());
        notice.setDescription(message.getDescription());
        return noticeRepository.save(notice);
    }

    public List<Notice> getAll() {
        return noticeRepository.findAll();
    }

    public Notice getById(String noticeId) {
        return noticeRepository.findById(noticeId).orElseThrow();
    }
}
