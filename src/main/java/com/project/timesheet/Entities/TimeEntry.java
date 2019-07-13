package com.project.timesheet.Entities;

import com.project.timesheet.BaseEntity;
import javafx.util.converter.LocalDateStringConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeEntry extends BaseEntity {

    private int userId;

    private String date;

    private int hours;

}
