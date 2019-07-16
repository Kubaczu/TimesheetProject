package com.project.timesheet.Entities;

import com.project.timesheet.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeEntry extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;

//    private int userId;

    private String date;

    private int hours;

}
