package com.project.timesheet.Entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public interface TimeEntryRepository extends JpaRepository<TimeEntry, Integer> {

    @Modifying
    @Query(value = "INSERT INTO time_entry (user_ID, date, hours) VALUES (:userID, :date, :hours)",
            nativeQuery = true)
    void addHour(
            @Param("userID") int userId,
            @Param("date") LocalDate date,
            @Param("hours") int hours);

//    @Modifying
//    @Query(value = "SELECT FEOM time_entry WHERE (user_ID) VALUES (:userId)", // TODO: skończyć
//            nativeQuery = true)
//    List<TimeEntry> showUserHours(
//            @Param("userID") int userId);

}
