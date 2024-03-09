package com.corner.academics.service.subject;

import com.corner.academics.dto.SubjectRequest;
import com.corner.academics.entity.Subjects;

import java.util.List;
import java.util.UUID;

public interface SubjectService {
    List<Subjects> getAllSubjects();

    Subjects getSubjectById(UUID subjectId);

    Subjects registerNewSubject(SubjectRequest subjectRequest);

    Boolean toggleStatus(UUID subjectId);

    Subjects updateSubject(UUID id, SubjectRequest subjectRequest);

    Boolean deleteSubject(UUID subjectId);
}
