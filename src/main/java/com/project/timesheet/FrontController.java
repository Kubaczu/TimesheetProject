package com.project.timesheet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {

    @RequestMapping("/")
    public String showMainMenu(){
        return "main-menu";
    }
}
