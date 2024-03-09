package com.corner.academics.service.exam;

import com.corner.academics.dto.ExamRequest;
import com.corner.academics.entity.Exam;

import java.util.List;
import java.util.UUID;

public interface ExamService {
    Exam updateExam(UUID id, ExamRequest examRequest);

    Boolean toggleStatus(UUID examId);

    Exam registerNewExam(ExamRequest examRequest);

    Exam getExamById(UUID examId);

    List<Exam> getAllExams();

    Boolean deleteExam(UUID examId);
}
