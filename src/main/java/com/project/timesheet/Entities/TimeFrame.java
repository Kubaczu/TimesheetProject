package com.project.timesheet.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeFrame {
    String dateFrom;
    String dateTo;
    int UserId;
}