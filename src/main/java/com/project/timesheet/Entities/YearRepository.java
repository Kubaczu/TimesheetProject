package com.project.timesheet.Entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.time.Month;


public interface YearRepository extends JpaRepository<Year, Integer> {
//    @Modifying
//    @Query("INSERT INTO timesheet.year (ID, month, day_of_month, day_of_week) VALUES ((?1), (?2), (?3), (?4))")
//    void addDay(int id, int dayOfMonth, Month month, DayOfWeek dayOfWeek);

}
