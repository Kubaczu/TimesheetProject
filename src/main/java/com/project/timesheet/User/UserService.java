package com.project.timesheet.User;

import com.project.timesheet.Entities.TimeEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

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

    public List<User> calculateSalary(List<TimeEntry> timeEntries) {
        System.out.println();
        List<User> userList = new ArrayList<>();
        User tempUser;
        for (TimeEntry timeEntry : timeEntries) {

            if (userList.contains(timeEntry.getUser())) {

                int newHours = timeEntry.getHours();
                int index = userList.indexOf(timeEntry.getUser());
                tempUser = userList.get(index);
                int prevHours = tempUser.getTempHours();

                userList.get(index).setTempHours(prevHours + newHours);
                System.out.println("Hours " + newHours + " to user " + userList.get(index).getFirstName() );
                System.out.println("Total hours for this user is " + userList.get(index).getTempHours());

            } else {

                tempUser = timeEntry.getUser();

                tempUser.setTempHours(timeEntry.getHours());
                userList.add(tempUser);
                System.out.println("User " + tempUser.getFirstName() + " and " + tempUser.getTempHours() + " hours added to list");

            }
            System.out.println();
        }

        for(int i = 0; i<userList.size();i++){
            userList.get(i).setSalary(userList.get(i).getTempHours()*userList.get(i).getRate());
            System.out.println("Calculated Salary is " + userList.get(i).getSalary() + " for " + userList.get(i).getTempHours() + " hours, with rate: " +  userList.get(i).getRate());
        }

        return userList;
    }

}
