package com.corner.academics.service.timetable;

import com.corner.academics.dto.TimetableRequest;
import com.corner.academics.entity.Timetable;

import java.util.List;
import java.util.UUID;

public interface TimetableService {
    List<Timetable> getAllTimetable();

    Timetable getTimetableById(UUID timetableId);

    Timetable getTimetableByClass(UUID classId);

    Timetable registerNewTimetable(TimetableRequest timetableRequest);

    Boolean toggleStatus(UUID timetableId);

    Timetable updateTimetable(UUID id, TimetableRequest timetableRequest);

    Boolean deleteTimetable(UUID timetableId);
}
