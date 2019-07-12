package com.project.timesheet.Entities;

import com.project.timesheet.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;
import java.time.Month;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Year extends BaseEntity {

    int dayOfMonth;

    @Enumerated(EnumType.STRING)
    Month month;

    @Enumerated(EnumType.STRING)
    DayOfWeek dayOfWeek;

}
