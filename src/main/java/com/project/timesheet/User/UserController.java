package com.project.timesheet.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/showUserMenu") // Pokazuje główne menu usera
    public String showUserMenu() {
        System.out.println("inside method: showUserMenu()");
        return "/user/user-menu";
    }

    @RequestMapping("/showCalculateForm") //    @TODO: To be deleted
    public String showCalculateForm(Model model) {
        System.out.println("inside method: showCalculateForm()");
        model.addAttribute("user", new User());
        return "/user/user-calculate";
    }

    @RequestMapping("/showUserCalculateSummary") //    @TODO: To be deleted
    public String showUserCalculateSummary(@ModelAttribute("user") User user) {
        System.out.println("inside method: showUserCalculateSummary()");
        System.out.println("tempHours: " + user.getTempHours());
        user.setRate(60);
        System.out.println("rate: " + user.getRate());
        user.setSalary(user.getRate() * user.getTempHours());
        System.out.println("salary: " + user.getSalary());
        return "/user/user-calculate-summary";
    }

    @RequestMapping("/showEnterHours")  // Pokazuje formularz do wpisywania godzin usera
    public String showEnterHours(Model model){
        System.out.println("inside method: showEnterHours()");
        model.addAttribute("user", new User());
        return "/user/user-enter-hours";
    }

    @RequestMapping("/showViewHours") // Pokazuje podsumowanie godzin usera
    public String showViewHours(Model model){
        System.out.println("inside method: showViewHours()");
        model.addAttribute("user", new User());
        return "/user/user-view-hours";
    }


}
