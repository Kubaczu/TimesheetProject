package com.project.timesheet.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void editUser(User user) {
//        user.setUserDepartment(userRepository.findById(user.getId()).get().getUserDepartment());
        userRepository.save(user);
    }

}
