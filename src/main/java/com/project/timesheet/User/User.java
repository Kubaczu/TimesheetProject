package com.project.timesheet.User;

import com.project.timesheet.BaseEntity;
import com.project.timesheet.Entities.TimeEntry;
import lombok.*;

import javax.persistence.*;
import java.util.List;


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

    @OneToMany(cascade = CascadeType.ALL)
    List<TimeEntry> entries;

    public void addEntry(TimeEntry timeEntry) {
        this.entries.add(timeEntry);
    }
}
