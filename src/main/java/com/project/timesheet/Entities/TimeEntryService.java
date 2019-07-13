package com.project.timesheet.Entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeEntryService {

    @Autowired
    TimeEntryRepository timeEntryRepository;

    public void addHour(int userId, String date, int hours) {
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setUserId(userId);
        timeEntry.setDate(date);
        timeEntry.setHours(hours);
        timeEntryRepository.save(timeEntry);
    }
}