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

    @Query(value = "SELECT * FROM time_entry te WHERE te.user_ID LIKE (:userID) ORDER BY te.date ASC", // TODO: skończyć
            nativeQuery = true)
    List<TimeEntry> showUserHours(
            @Param("userID") int userID);

    @Query(value = "SELECT * FROM time_entry te WHERE (te.user_ID = :loggedUserId) AND (te.date BETWEEN :dateFrom AND :dateTo) ORDER BY te.date ASC ", // TODO: skończyć
            nativeQuery = true)
    List<TimeEntry> showUserHoursByDate(@Param("loggedUserId") int loggedUserId,
                                        @Param("dateFrom") String dateFrom,
                                        @Param("dateTo") String dateTo);

    @Query(value = "SELECT * FROM time_entry te WHERE te.date BETWEEN :dateFrom AND :dateTo ORDER BY te.date ASC ", // TODO: skończyć
            nativeQuery = true)
    List<TimeEntry> showHoursByDate(@Param("dateFrom") String dateFrom,
                                    @Param("dateTo") String dateTo);
}
