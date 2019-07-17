package com.project.timesheet.User;

import com.project.timesheet.Entities.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    int loggedUserId;

    @Autowired
    TimeEntryRepository timeEntryRepository;

    @Autowired
    TimeEntryService timeEntryService;

    @RequestMapping("/showUserMenu") // Pokazuje główne menu usera
    public String showUserMenu(
            @ModelAttribute("loggedUser") User user) {
        if (loggedUserId == 0) {
            loggedUserId = user.getId();
        }
        System.out.println("Logged user ID is : " + loggedUserId);
        System.out.println("inside method: showUserMenu()");
        return "/user/user-menu";
    }

    @RequestMapping("/showCalculateForm") //    @TODO: To be deleted
    public String showCalculateForm(
            Model model) {
        System.out.println("inside method: showCalculateForm()");
        model.addAttribute("user", new User());
        return "/user/user-calculate";
    }

    @RequestMapping("/showUserCalculateSummary") //    @TODO: To be deleted
    public String showUserCalculateSummary(
            @ModelAttribute("user") User user) {
        System.out.println("inside method: showUserCalculateSummary()");
        System.out.println("tempHours: " + user.getTempHours());
        user.setRate(60);
        System.out.println("rate: " + user.getRate());
        user.setSalary(user.getRate() * user.getTempHours());
        System.out.println("salary: " + user.getSalary());
        return "/user/user-calculate-summary";
    }

    @RequestMapping("/showEnterHours")  // Pokazuje formularz do wpisywania godzin usera
    public String showEnterHours(
            Model model) {
        System.out.println("inside method: showEnterHours()");
        model.addAttribute("user", new User());
        model.addAttribute("timeEntry", new TimeEntry());
        String confirmationMessage = "";
        model.addAttribute("message", confirmationMessage);
        return "/user/user-enter-hours";
    }

    @RequestMapping("/addHours")
    public String addHours(
            Model model,
            @ModelAttribute("user") User user,
            @ModelAttribute("timeEntry") TimeEntry timeEntry
    ) {
        System.out.println("wpisano " + user.getTempHours() + "h dnia:" + timeEntry.getDate());
        timeEntryService.addHour(loggedUserId, timeEntry.getDate(), user.getTempHours());
        String confirmationMessage = "Successfully added " + user.getTempHours() + " hours to " + timeEntry.getDate();
        model.addAttribute("message", confirmationMessage);
        return "/user/user-enter-hours";
    }

    @RequestMapping("/showViewHours") // Pokazuje podsumowanie godzin usera
    public String showHours(Model model) {

        model.addAttribute("userEntries", timeEntryRepository.showUserHours(loggedUserId));
        model.addAttribute("dateFrame", new TimeFrame());


        System.out.println(loggedUserId);
        return "/user/user-view-hours";
    }

    @RequestMapping("/showHoursByDate")
    public String showHoursByDate(
            @ModelAttribute("dateFrame") TimeFrame timeFrame,
            Model model
    ) {
        System.out.println("dateFrom: " + timeFrame.getDateFrom() + " dateTo: " + timeFrame.getDateTo());
        model.addAttribute("userEntries", timeEntryRepository.showUserHoursByDate(loggedUserId, timeFrame.getDateFrom(), timeFrame.getDateTo()));
        return "/user/user-view-hours";
    }


}
