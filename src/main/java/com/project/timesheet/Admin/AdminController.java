package com.project.timesheet.Admin;

import com.project.timesheet.User.User;
import com.project.timesheet.User.UserDepartment;
import com.project.timesheet.User.UserRepository;
import com.project.timesheet.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.DayOfWeek;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    //  Main Admin Menu
    @RequestMapping("/showAdminMenu")
    public String showAdminMenu() {
        System.out.println("inside method: showAdminMenu()");
        return "/admin/admin-menu";
    }

    //  Show hours
    @RequestMapping("/showHoursViewMenu")
    public String showHoursViewMenu(Model model) {
        model.addAttribute("user", new User());
        return "/admin/admin-hours-view-menu";
    }

    //  Edit Hours
    @RequestMapping("showHoursEditMenu")
    public String showHoursEditMenu(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("daysOfWeek", DayOfWeek.values());
        return "/admin/admin-hours-edit-menu";
    }

    //  Edit Users
    @RequestMapping("showUserEditForm")
    public String showUserEditForm(Model model) {

        model.addAttribute("departmentTypes", UserDepartment.values());
        model.addAttribute("user", new User());
        model.addAttribute("usersList", userRepository.findAll());
        //DayOfWeek <- Enum
        return "/admin/admin-user-edit-form";
    }

    @RequestMapping("addUser")
    public String addUser(@ModelAttribute("user") User user) {
        System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getRate());
        userService.addUser(user);
        return "redirect:/admin/showAdminMenu";
    }

    @RequestMapping("editUser") //  @TODO: skończyć
    public String editUser(@ModelAttribute("user") User user)  {
        System.out.println(user.getId());
        userService.editUser(user);
        return "redirect:/admin/showAdminMenu"; //  @TODO: poprawić
    }

    //  Calculate
    @RequestMapping("showSalaryCalculationMenu")
    public String showSalaryCalculationMenu(Model model) {
        model.addAttribute("user", new User());
        return "/admin/admin-salary-calculation-menu";
    }
}
