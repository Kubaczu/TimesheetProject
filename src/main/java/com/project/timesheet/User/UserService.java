package com.project.timesheet.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void editUser(User user) {
        userRepository.deleteById(user.getId());
        userRepository.save(user);
    }
}
