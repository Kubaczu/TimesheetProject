package com.project.timesheet.User;

import com.project.timesheet.Entities.BaseEntity;
import com.project.timesheet.Entities.TimeEntry;
import com.project.timesheet.config.Roles;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {


    @NotBlank (message = " Please enter first name")
    String firstName;

    @NotBlank (message = " Please enter last name")
    String lastName;

    String username;
    String password;

    @NotNull (message = " Please enter rate")
    @Range (min = 20, max = 200, message = "Rate range must be between 20-200")
    Integer rate;

    @NotNull (message = "Please select a department")
    @Enumerated(EnumType.STRING)
    UserDepartment userDepartment;

    @Enumerated(EnumType.STRING)
    Roles roles;

    Integer tempHours;
    Integer salary;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_ID")

    List<TimeEntry> entries;


    public void addEntry(TimeEntry timeEntry) {
        this.entries.add(timeEntry);
    }
}
