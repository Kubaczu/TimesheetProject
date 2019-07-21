package com.project.timesheet;

import com.project.timesheet.User.UserRepository;
import com.project.timesheet.config.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String showMainMenu(Model model){
        boolean isAdmin = isUserInRole(Roles.ROLE_ADMIN);
        System.out.println(getCurrentUserName());
        if (isAdmin) {
            return "redirect:admin/showAdminMenu";
        } else {
            System.out.println("is Admin is false");
            return "redirect:/user/showUserMenu";
        }

    }
    public boolean isUserInRole(Roles role) {
        Authentication userAuthentication =
                SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Inside Front Controller");
        if (userAuthentication == null) {
            return false;
        }
        return userAuthentication
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equalsIgnoreCase(role.getAuthorityName()));
    }

    public String getCurrentUserName() {
        Authentication userAuthentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (userAuthentication == null) {
            return null;
        }
        return userAuthentication.getName();
    }

    public String getCurrentId() {
        Authentication userAuthentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (userAuthentication == null) {
            return null;
        }
        return userAuthentication.getName();
    }
}

