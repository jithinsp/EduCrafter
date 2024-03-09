package com.corner.notification.repository;

import com.corner.notification.entity.Notice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface NoticeRepository extends MongoRepository<Notice, String> {
//    public Notice findByFirstName(String firstName);
//    public List<Notice> findByLastName(String lastName);
}
