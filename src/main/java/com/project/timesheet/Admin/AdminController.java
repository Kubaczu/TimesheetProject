package com.project.timesheet.Admin;

import com.project.timesheet.Entities.TimeEntry;
import com.project.timesheet.Entities.TimeEntryRepository;
import com.project.timesheet.Entities.TimeEntryService;
import com.project.timesheet.Entities.TimeFrame;
import com.project.timesheet.User.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.project.timesheet.config.Roles.ROLE_USER;

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

    TimeEntry entryToEdit = new TimeEntry();
    int userIdToEdit;

    //  Main Admin Menu
    @RequestMapping("/showAdminMenu")
    public String showAdminMenu() {
        System.out.println("inside method: showAdminMenu()");
        return "/admin/admin-menu";
    }

    //  Show hours // Vaildation Complete
    @RequestMapping("/showHoursViewMenu")
    public String showHoursViewMenu(
            Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("usersList", userRepository.findAll());
        model.addAttribute("timeFrame", new TimeFrame());
        return "/admin/admin-hours-view-menu";
    }

    @RequestMapping("/showHoursAll")
    public String showHoursAll(
            Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("userEntries", timeEntryRepository.findAll());
        model.addAttribute("usersList", userRepository.findAll());
        model.addAttribute("timeFrame", new TimeFrame());
        return "/admin/admin-hours-view-menu";
    }

    @RequestMapping("/showHoursByUser")
    public String showHoursByUser(
            @RequestParam int userHoursId,
            @ModelAttribute("timeFrame") TimeFrame timeFrame,
            Model model) {

        List<TimeEntry> userTimeEntries = timeEntryRepository.showUserHours(userHoursId);
        model.addAttribute("userEntries", userTimeEntries);
        model.addAttribute("usersList", userRepository.findAll());
        return "/admin/admin-hours-view-menu";
    }

    @RequestMapping("/showHoursByDate")
    public String showHoursByDate(
            @Valid @ModelAttribute("timeFrame") TimeFrame timeFrame,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usersList", userRepository.findAll());
            System.out.println("has some errors inside 'showHoursByDate' ");
            return "/admin/admin-hours-view-menu";
        }
        model.addAttribute("usersList", userRepository.findAll());
        model.addAttribute("userEntries", timeEntryRepository.showHoursByDate(timeFrame.getDateFrom(), timeFrame.getDateTo()));
        return "/admin/admin-hours-view-menu";
    }


    //  Edit Hours  // Vaildation Complete
    @RequestMapping("/showHoursEditMenu")
    public String showHoursEditMenu(
            Model model) {
        model.addAttribute("entryToEdit", new TimeEntry());
        model.addAttribute("userEntries", timeEntryRepository.findAll());
        String message = "";
        model.addAttribute("message", message);
        return "/admin/admin-hours-edit-menu";
    }

    @GetMapping("/editHours")
    public String editHours(
            @RequestParam Integer id,
            Model model) {
//        userIdToEdit = id;
        System.out.println("Entry id:" + id);
        List<Integer> listOfId = new ArrayList<>();
        listOfId.add(id);
        List<TimeEntry> timeEntryList = timeEntryRepository.findAllById(listOfId);
        model.addAttribute("entryToChange", timeEntryList.get(0));
        model.addAttribute("entryNew", new TimeEntry());
        entryToEdit.setUser(timeEntryList.get(0).getUser());
        entryToEdit.setId(timeEntryList.get(0).getId());

        return "/admin/admin-hours-edit-entry";
    }

    @PostMapping("/saveHours")
    public String saveHours(

            @Valid @ModelAttribute("entryNew") TimeEntry entryNew,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("entryToEdit", new TimeEntry());
            model.addAttribute("userEntries", timeEntryRepository.findAll());
            String message = "Incorrect values, try again";
            model.addAttribute("message", message);
            return "/admin/admin-hours-edit-menu";
        }
        entryNew.setUser(entryToEdit.getUser());
        entryNew.setId(entryToEdit.getId());
        System.out.println(entryNew.getDate() + " " + entryNew.getHours() + " " + entryNew.getUser() + " " + entryNew.getId());
        timeEntryService.editHour(entryNew);
        return "/admin/admin-menu";
    }

    //  Edit Users
    @RequestMapping("/showUserEditForm")
    public String showUserEditForm(
            Model model) {

        model.addAttribute("departmentTypes", UserDepartment.values());
        model.addAttribute("user", new User());
        List<User> userList = userRepository.findAll();
        System.out.println(userList.size());
        model.addAttribute("usersList", userList);
        return "/admin/admin-user-edit-form";
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/addUser")
    public String addUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            Model model
            ) {
        if (bindingResult.hasErrors()) {
            System.out.println("has some errors inside 'addUser'");
            List<User> userList = userRepository.findAll();
            model.addAttribute("usersList", userList);
            model.addAttribute("departmentTypes", UserDepartment.values());
            return "/admin/admin-user-edit-form";
        }
        System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getRate());

        String randomPassword = RandomStringUtils.randomAlphanumeric(8);
        System.out.println(randomPassword);
        String encodedPassword = passwordEncoder.encode(randomPassword);

        user.setUsername(user.getFirstName().concat(user.getLastName()));
        user.setPassword(encodedPassword);
        user.setRoles(ROLE_USER);
        userService.addUser(user);

        return "redirect:/admin/showUserEditForm";
    }

    @RequestMapping("/editUser")
    public String editUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("has some errors inside 'editUser'");
            List<User> userList = userRepository.findAll();
            model.addAttribute("usersList", userList);
            model.addAttribute("departmentTypes", UserDepartment.values());
            return "/admin/admin-user-edit-form";
        }
        System.out.println(user.getId());
        userService.editUser(user);
        return "redirect:/admin/showUserEditForm"; //
    }

    //  Calculate   // Vaildation Complete
    @RequestMapping("/showSalaryCalculationMenu")
    public String showSalaryCalculationMenu(
            Model model) {
        model.addAttribute("timeFrame", new TimeFrame());
        model.addAttribute("usersList", userRepository.findAll());
        return "/admin/admin-salary-calculation-menu";
    }

    @RequestMapping("/showSalaryCalculationFormByTime")
    public String showSalaryCalculationForm(
            @Valid @ModelAttribute("timeFrame") TimeFrame timeFrame,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usersList", userRepository.findAll());
            return "/admin/admin-salary-calculation-menu";
        }

        System.out.println(timeFrame.getDateFrom() + " " + timeFrame.getDateTo());
        model.addAttribute("calculatedSalary", userService.calculateSalary(timeEntryRepository.showHoursByDate(timeFrame.getDateFrom(), timeFrame.getDateTo())));
        return "/admin/admin-salary-calculation-form";
    }

    @RequestMapping("/showSalaryCalculationFormByUser")
    public String showSalaryCalculationFormByUser(
            @Valid @ModelAttribute("timeFrame") TimeFrame timeFrame,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usersList", userRepository.findAll());
            return "/admin/admin-salary-calculation-menu";
        }
        System.out.println(timeFrame.getUserId() + " " + timeFrame.getDateFrom() + " " + timeFrame.getDateTo());
        model.addAttribute("calculatedSalary", userService.calculateSalary(timeEntryRepository.showUserHoursByDate(timeFrame.getUserId(), timeFrame.getDateFrom(), timeFrame.getDateTo())));
        return "/admin/admin-salary-calculation-form";
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public class EntryToEdit {
        int oldEntryId;
        TimeEntry oldEntry;
        TimeEntry newEntry;
        private String date;
        private int hours;
    }

}
