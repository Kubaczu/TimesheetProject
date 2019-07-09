package com.project.timesheet.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/showAdminMenu")
    public String showAdminMenu(){
        System.out.println("inside method: showAdminMenu()");
        return "admin-menu";
    }
}
