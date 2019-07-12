package com.project.timesheet.Entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.Month;


public interface YearRepository extends JpaRepository<Year, Integer> {
    @Modifying
    @Query(value = "INSERT INTO year (month, day_of_month, day_of_week) VALUES (:month, :day_of_month, :day_of_week)",
    nativeQuery = true)
    void addDay(
            @Param("month") String month,
            @Param("day_of_month") int day_Of_Month,
            @Param("day_of_week") int day_Of_Week);

}
