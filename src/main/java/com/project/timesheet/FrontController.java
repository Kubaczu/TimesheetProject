package com.project.timesheet;

import com.project.timesheet.User.User;
import com.project.timesheet.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String showMainMenu(Model model){
        model.addAttribute("loggedUser", new User());
        model.addAttribute("usersList", userRepository.findAll());
        return "main-menu";
    }
}
