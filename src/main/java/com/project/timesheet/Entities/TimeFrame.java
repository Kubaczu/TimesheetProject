package com.project.timesheet.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class TimeFrame {


    @NotBlank (message = " Please select date")
    private String dateFrom;


    @NotBlank (message = " Please select date")
    private String dateTo;

    int userId;
}