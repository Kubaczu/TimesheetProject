package com.project.timesheet.User;

import com.project.timesheet.Entities.*;
import com.project.timesheet.FrontController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/showUserMenu") // Pokazuje główne menu usera
    public String showUserMenu(
            @ModelAttribute("loggedUser") User user) {
        if (loggedUserId == 0) {
            FrontController frontController = new FrontController();
            String loggedUserName = frontController.getCurrentUserName();
            loggedUserId = userRepository.findUserByUserNameLike(loggedUserName);

        }
        System.out.println("Logged user ID is : " + loggedUserId);
        System.out.println("inside method: showUserMenu()");
        return "/user/user-menu";
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
            @Valid @ModelAttribute("timeEntry") TimeEntry timeEntry,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/user/user-enter-hours";
        }

        List<TimeEntry> timeEntryList = timeEntryRepository.findAll();
        for (TimeEntry timeEntrySingle : timeEntryList){
            if (timeEntrySingle.getDate().equals(timeEntry.getDate())){
                String confirmationMessage =
                        "Entry for this date already exists " + timeEntry.getDate();
                model.addAttribute("message", confirmationMessage);
                return "/user/user-enter-hours";
            }
        }

        System.out.println("wpisano " + timeEntry.getHours() + "h dnia:" + timeEntry.getDate());

        timeEntryService.addHour(
                loggedUserId, timeEntry.getDate(), timeEntry.getHours());

        String confirmationMessage =
                "Successfully added " + timeEntry.getHours() + " hours to " + timeEntry.getDate();

        model.addAttribute("message", confirmationMessage);
        return "/user/user-enter-hours";

    }

    @RequestMapping("/showViewHours") // Pokazuje podsumowanie godzin usera
    public String showHours(Model model) {

        model.addAttribute("userEntries", timeEntryRepository.showUserHours(loggedUserId));
        model.addAttribute("timeFrame", new TimeFrame());

        System.out.println(loggedUserId);
        return "/user/user-view-hours";
    }

    @RequestMapping("/showHoursByDate")
    public String showHoursByDate(
            @Valid @ModelAttribute("timeFrame") TimeFrame timeFrame,
            BindingResult bindingResult,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userEntries", timeEntryRepository.showUserHours(loggedUserId));
            model.addAttribute("timeFrame", new TimeFrame());
            return "/user/user-view-hours";
        }
//        if (timeFrame.getDateFrom() == ""){
//            timeFrame.setDateFrom("0001-01-01");
//            System.out.println("Date 'from' set to default");
//        }
//
//        if (timeFrame.getDateTo()== ""){
//            timeFrame.setDateTo("9999-12-31");
//            System.out.println("Date 'to' set to default");
//        }

        System.out.println("dateFrom: " + timeFrame.getDateFrom() + " dateTo: " + timeFrame.getDateTo());
        LocalDate dateFromLocal = LocalDate.parse(timeFrame.getDateFrom());
        LocalDate dateToLocal = LocalDate.parse(timeFrame.getDateTo());

        if (dateFromLocal.isAfter(dateToLocal)) {
            String tempDate;
            tempDate = timeFrame.getDateFrom();
            timeFrame.setDateFrom(timeFrame.getDateTo());
            timeFrame.setDateTo(tempDate);
        }
        System.out.println("dateFrom: " + timeFrame.getDateFrom() + " dateTo: " + timeFrame.getDateTo());
        model.addAttribute("userEntries", timeEntryRepository.showUserHoursByDate(loggedUserId, timeFrame.getDateFrom(), timeFrame.getDateTo()));
        return "/user/user-view-hours";
    }

}
