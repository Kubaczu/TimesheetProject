package com.project.timesheet.User;

import com.project.timesheet.Entities.BaseEntity;
import com.project.timesheet.Entities.TimeEntry;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    // No need to validate, temp fields only

    Integer tempHours;
    Integer salary;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_ID")

    List<TimeEntry> entries;


    public void addEntry(TimeEntry timeEntry) {
        this.entries.add(timeEntry);
    }
}
