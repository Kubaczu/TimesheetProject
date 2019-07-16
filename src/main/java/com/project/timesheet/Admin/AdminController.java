package com.project.timesheet.Admin;

import com.project.timesheet.Entities.TimeEntry;
import com.project.timesheet.Entities.TimeEntryRepository;
import com.project.timesheet.Entities.TimeEntryService;
import com.project.timesheet.User.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TimeEntryService timeEntryService;

    @Autowired
    TimeEntryRepository timeEntryRepository;

    //  Main Admin Menu
    @RequestMapping("/showAdminMenu")
    public String showAdminMenu() {
        System.out.println("inside method: showAdminMenu()");
        return "/admin/admin-menu";
    }

    //show main menu


    //  Show hours
    @RequestMapping("/showHoursViewMenu")
    public String showHoursViewMenu(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("usersList", userRepository.findAll());
        return "/admin/admin-hours-view-menu";
    }

    @RequestMapping("/showHoursAll")
    public String showHoursAll(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("usersList", userRepository.findAll());
        model.addAttribute("userEntries", timeEntryRepository.findAll());
        return "/admin/admin-hours-view-menu";
    }

    @RequestMapping("/showHoursByUser")
    public String showHoursByUser(
            @ModelAttribute("user") User user, Model model){
        List<Integer> usersId = new ArrayList<>();
        usersId.add(user.getId());
        List<TimeEntry> userTimeEntries = timeEntryRepository.showUserHours(user.getId());
        model.addAttribute("userEntries", userTimeEntries);
        model.addAttribute("usersList", userRepository.findAll());
        return "/admin/admin-hours-view-menu";
    }

    @RequestMapping("/showHoursByDate")
    public String showHoursByDate(
            @ModelAttribute("dateFrame") UserController.TimeFrame timeFrame,
            Model model
    ){
        model.addAttribute("usersList", userRepository.findAll());
        model.addAttribute("userEntries", timeEntryRepository.showHoursByDate(timeFrame.getDateFrom(), timeFrame.getDateTo()));
        return "/admin/admin-hours-view-menu";
    }


    //  Edit Hours
    @RequestMapping("/showHoursEditMenu")
    public String showHoursEditMenu(Model model) {
        model.addAttribute("entryToEdit", new TimeEntry());
        model.addAttribute("userEntries", timeEntryRepository.findAll());
        return "/admin/admin-hours-edit-menu";
    }

    @RequestMapping("/editHours")
    public String editHours(
            @ModelAttribute ("entryToEdit") TimeEntry timeEntry,
            Model model) {
        System.out.println("TimeEntry: " + timeEntry.getHours() + timeEntry.getDate());
        return "/admin/admin-hours-edit-entry";
    }

    //  Edit Users
    @RequestMapping("/showUserEditForm")
    public String showUserEditForm(Model model) {

        model.addAttribute("departmentTypes", UserDepartment.values());
        model.addAttribute("user", new User());
        model.addAttribute("usersList", userRepository.findAll());
        //DayOfWeek <- Enum
        return "/admin/admin-user-edit-form";
    }

    @RequestMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getRate());
        userService.addUser(user);
        return "redirect:/admin/showAdminMenu";
    }

    @RequestMapping("/editUser") //  @TODO: skończyć
    public String editUser(@ModelAttribute("user") User user)  {
        System.out.println(user.getId());
        userService.editUser(user);
        return "redirect:/admin/showAdminMenu"; //  @TODO: poprawić
    }

    //  Calculate
    @RequestMapping("/showSalaryCalculationMenu")
    public String showSalaryCalculationMenu(Model model) {
        model.addAttribute("user", new User());
        return "/admin/admin-salary-calculation-menu";
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public class EntryToEdit{
        int oldEntryId;
        TimeEntry oldEntry;
        TimeEntry newEntry;
        private String date;
        private int hours;
    }

}
