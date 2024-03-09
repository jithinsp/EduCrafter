package com.corner.academics.service.timetable;

import com.corner.academics.dto.TimetableRequest;
import com.corner.academics.entity.Classes;
import com.corner.academics.entity.Timetable;
import com.corner.academics.repository.ClassesRepository;
import com.corner.academics.repository.TimetableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TimetableServiceImpl implements TimetableService{
    @Autowired
    TimetableRepository timetableRepository;
    @Autowired
    ClassesRepository classesRepository;

    public List<Timetable> getAllTimetable() {
        return timetableRepository.findAll();
    }

    public Timetable getTimetableById(UUID timetableId) {
        return timetableRepository.findById(timetableId)
                .orElseThrow(()->new EntityNotFoundException("Timetable not found"));
    }

    public Timetable getTimetableByClass(UUID classId) {
        Classes classes = classesRepository.findById(classId)
                .orElseThrow(()->new EntityNotFoundException("Grade not found"));
        return timetableRepository.findByClasses(classes);
    }

    public Timetable registerNewTimetable(TimetableRequest timetableRequest) {
        Timetable timetable = new Timetable();
        BeanUtils.copyProperties(timetable,timetableRequest);
        return timetableRepository.save(timetable);
    }

    public Boolean toggleStatus(UUID timetableId) {
        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(()->new EntityNotFoundException("Timetable not found"));
        timetable.setIsEnabled(!timetable.getIsEnabled());
        timetableRepository.save(timetable);
        return timetable.getIsDeleted();
    }

    public Timetable updateTimetable(UUID id, TimetableRequest timetableRequest) {
        Timetable timetable = timetableRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Timetable not found"));
        timetable.setSlot(timetableRequest.getSlot());
        timetable.setClasses(timetableRequest.getClasses());
        timetable.setSubjects(timetableRequest.getSubjects());
        timetable.setTeacherId(timetableRequest.getTeacherId());
        return timetableRepository.save(timetable);
    }

    public Boolean deleteTimetable(UUID timetableId) {
        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(()->new EntityNotFoundException("Timetable not found"));
        timetable.setIsDeleted(!timetable.getIsDeleted());
        timetableRepository.save(timetable);
        return timetable.getIsDeleted();
    }
}
