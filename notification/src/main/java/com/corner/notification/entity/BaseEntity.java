//package com.corner.notification.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.data.annotation.Id;
//
//import java.sql.Timestamp;
//import java.util.UUID;
//
//@Getter
//@Setter
//@MappedSuperclass
//public class BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//
//    @CreationTimestamp
//    @Column(updatable = false)
//    private Timestamp createdDate;
//
//    @UpdateTimestamp
//    private Timestamp lastModifiedDate;
//
//    private boolean isEnabled;
//
//}