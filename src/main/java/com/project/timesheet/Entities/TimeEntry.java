package com.project.timesheet.Entities;

import com.project.timesheet.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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

    @NotBlank (message = " Please select date")
    private String date;

    @NotNull(message = " Please enter hours")
    @Range(min = 0, max = 12, message = "Hours range must be between 1-12")
    private Integer hours;

}
