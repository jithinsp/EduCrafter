package com.corner.academics.service.result;

import com.corner.academics.dto.ResultRequest;
import com.corner.academics.entity.Result;
import com.corner.academics.repository.ResultRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResultServiceImpl implements ResultService{
    @Autowired
    ResultRepository resultRepository;

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Result getResultById(UUID resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(()->new EntityNotFoundException("Result not found"));
    }

    public Result registerNewResult(ResultRequest resultRequest) {
        Result newResult = new Result();
        BeanUtils.copyProperties(newResult,resultRequest);
        return resultRepository.save(newResult);
    }

    public Boolean toggleStatus(UUID resultId) {
        Result toggleResult = resultRepository.findById(resultId)
                .orElseThrow(()->new EntityNotFoundException("Result not found"));
        toggleResult.setIsEnabled(!toggleResult.getIsEnabled());
        return toggleResult.getIsDeleted();
    }

    public Result updateResult(UUID id, ResultRequest resultRequest) {
        Result updateResult = resultRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Result not found"));
        updateResult.setMark(resultRequest.getMark());
        updateResult.setExam(resultRequest.getExam());
        updateResult.setClasses(resultRequest.getClasses());
        updateResult.setSubject(resultRequest.getSubject());
        updateResult.setStudentId(resultRequest.getStudentId());
        return resultRepository.save(updateResult);
    }

    public Boolean deleteResult(UUID resultId) {
        Result toggleResult = resultRepository.findById(resultId)
                .orElseThrow(()->new EntityNotFoundException("Result not found"));
        toggleResult.setIsDeleted(!toggleResult.getIsDeleted());
        return toggleResult.getIsDeleted();
    }
}