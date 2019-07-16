package com.project.timesheet.Entities;

import com.project.timesheet.User.User;
import com.project.timesheet.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeEntryService {

    @Autowired
    TimeEntryRepository timeEntryRepository;

    @Autowired
    UserRepository userRepository;

    public void addHour(int userId, String date, int hours) {
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setDate(date);
        timeEntry.setHours(hours);
        timeEntry.setUser(userRepository.findById(userId).get());
        timeEntryRepository.save(timeEntry);
    }

    public void editHour(TimeEntry timeEntry) {
        timeEntryRepository.save(timeEntry);
    }
}