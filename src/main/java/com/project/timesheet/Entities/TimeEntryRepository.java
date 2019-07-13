package com.project.timesheet.Entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;


public interface TimeEntryRepository extends JpaRepository<TimeEntry, Integer> {

    @Modifying
    @Query(value = "INSERT INTO time_entry (user_ID, date, hours) VALUES (:userID, :date, :hours)",
            nativeQuery = true)
    void addHour(
            @Param("userID") int userId,
            @Param("date") LocalDate date,
            @Param("hours") int hours);

}
