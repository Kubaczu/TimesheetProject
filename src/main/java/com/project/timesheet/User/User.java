package com.project.timesheet.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    String firstName;
    String lastName;

    Integer rate;

    Integer tempHours;

    Integer salary;
}
