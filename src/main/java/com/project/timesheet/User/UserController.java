package com.project.timesheet.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/showUserMenu")
    public String showUserMenu() {
        System.out.println("inside method: showUserMenu()");
        return "user-menu";
    }

    @RequestMapping("/showCalculateForm")
    public String showCalculateForm(Model model) {
        System.out.println("inside method: showCalculateForm()");
        model.addAttribute("user", new User());
        return "user-calculate";
    }

    //  olać:  @GetMapping("/showUserCalculateSummary") // nie wyswietla zapytania w pasku adresu, odbiera method="post"
    @RequestMapping("/showUserCalculateSummary")
    public String showUserCalculateSummary(@ModelAttribute("user") User user) {
        System.out.println("inside method: showUserCalculateSummary()");
        System.out.println("tempHours: " + user.getTempHours());
        user.setRate(60);
        System.out.println("rate: " + user.getRate());
        user.setSalary(user.getRate() * user.getTempHours());
        System.out.println("salary: " + user.getSalary());
        return "user-calculate-summary";
    }


}
