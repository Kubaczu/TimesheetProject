package com.project.timesheet.User;

import com.project.timesheet.BaseEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    String firstName;
    String lastName;

    Integer rate;

    @Enumerated(EnumType.STRING)
    UserDepartment userDepartment;

    Integer tempHours;
    Integer salary;
}
