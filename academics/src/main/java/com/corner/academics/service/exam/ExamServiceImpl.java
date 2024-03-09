package com.corner.academics.service.exam;

import com.corner.academics.dto.ExamRequest;
import com.corner.academics.entity.Exam;
import com.corner.academics.repository.ExamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExamServiceImpl implements ExamService{
    @Autowired
    ExamRepository examRepository;

    public Exam updateExam(UUID id, ExamRequest examRequest) {
        Exam toggleExam = examRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Exam not found"));
        toggleExam.setName(examRequest.getName());
        toggleExam.setDate(examRequest.getDate());
        toggleExam.setSubject(examRequest.getSubject());
        toggleExam.setClasses(examRequest.getClasses());
        toggleExam.setTimeslot(examRequest.getTimeslot());
        return examRepository.save(toggleExam);
    }

    public Boolean toggleStatus(UUID examId) {
        Exam toggleExam = examRepository.findById(examId)
                .orElseThrow(()->new EntityNotFoundException("Exam not found"));
        toggleExam.setIsEnabled(!toggleExam.getIsEnabled());
        return toggleExam.getIsDeleted();
    }

    public Exam registerNewExam(ExamRequest examRequest) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(exam,examRequest);
        return examRepository.save(exam);
    }

    public Exam getExamById(UUID examId) {
        return examRepository.findById(examId)
                .orElseThrow(()->new EntityNotFoundException("Exam not found"));
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Boolean deleteExam(UUID examId) {
        Exam toggleExam = examRepository.findById(examId)
                .orElseThrow(()->new EntityNotFoundException("Exam not found"));
        toggleExam.setIsDeleted(!toggleExam.getIsDeleted());
        return toggleExam.getIsDeleted();
    }
}
