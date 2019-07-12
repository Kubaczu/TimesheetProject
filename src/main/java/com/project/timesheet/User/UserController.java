package com.project.timesheet.User;

import com.project.timesheet.Entities.Year;
import com.project.timesheet.Entities.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Month;

@Controller
@RequestMapping("/user")
public class UserController {

    int loggedUserId;

    @Autowired
    YearService yearService;

    @RequestMapping("/showUserMenu") // Pokazuje główne menu usera
    public String showUserMenu(@ModelAttribute("loggedUser") User user) {
        System.out.println("Logged user ID is : " + user.getId());
        loggedUserId = user.getId();
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
        model.addAttribute("months", Month.values());
        model.addAttribute("user", new User());
        model.addAttribute("day", new Year());
        return "/user/user-enter-hours";
    }
    @RequestMapping("/addHours")
    public String addHours(@ModelAttribute("user") User user, @ModelAttribute("day") Year year){
        System.out.println("wpisano " + user.getTempHours() + "h dnia:" + year.getDayOfMonth() + " " + year.getMonth());
//        @TODO: Tu trzeba wpisac metode zapisu godzin do tebeli hours
        return "/user/user-enter-hours";
    }


    @RequestMapping("/showViewHours") // Pokazuje podsumowanie godzin usera
    public String showViewHours(Model model){
        System.out.println("inside method: showViewHours()");
        model.addAttribute("user", new User());
        return "/user/user-view-hours";
    }


}
