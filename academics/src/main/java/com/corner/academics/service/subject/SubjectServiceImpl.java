package com.corner.academics.service.subject;

import com.corner.academics.dto.SubjectRequest;
import com.corner.academics.entity.Subjects;
import com.corner.academics.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    SubjectRepository subjectRepository;

    public List<Subjects> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subjects getSubjectById(UUID subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(()->new EntityNotFoundException("Subject not found"));
    }

    public Subjects registerNewSubject(SubjectRequest subjectRequest) {
        Subjects newSubject = new Subjects();
        BeanUtils.copyProperties(subjectRequest,newSubject);
        return subjectRepository.save(newSubject);
    }

    public Boolean toggleStatus(UUID subjectId) {
        Subjects toggleSubject = subjectRepository.findById(subjectId)
                .orElseThrow(()->new EntityNotFoundException("Subject Not found"));
        toggleSubject.setIsEnabled(
                toggleSubject.getIsEnabled() != null ? !toggleSubject.getIsEnabled() : false);
        subjectRepository.save(toggleSubject);
        return toggleSubject.getIsDeleted();
    }

    public Subjects updateSubject(UUID id, SubjectRequest subjectRequest) {
        Subjects updateSubject = subjectRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Subject Not found"));
        updateSubject.setName(subjectRequest.getName());
        updateSubject.setDescription(subjectRequest.getDescription());
        return subjectRepository.save(updateSubject);
    }

    public Boolean deleteSubject(UUID subjectId) {
        Subjects toggleSubject = subjectRepository.findById(subjectId)
                .orElseThrow(()->new EntityNotFoundException("Subject Not found"));
        toggleSubject.setIsDeleted(!toggleSubject.getIsDeleted());
        subjectRepository.save(toggleSubject);
        return toggleSubject.getIsDeleted();
    }
}
