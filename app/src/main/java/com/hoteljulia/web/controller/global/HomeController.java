package com.hoteljulia.web.controller.global;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hoteljulia.core.service.user.UserService;


@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String landingPage(Model model) {
        model.addAttribute("user", userService.getCurrentUser().orElse(null));
        return "public/landing-page";
    }

    @GetMapping("/login")
    public String loginPage() { return pageIfNotAuthenticated("auth/login"); }

    @GetMapping("/sign-up")
    public String registerPage() { return pageIfNotAuthenticated("auth/sign-up"); }


    public String pageIfNotAuthenticated(String pageName) {
        return userService.getCurrentRole().map(
            role -> switch (role) {
                case MANAGER -> "redirect:/manager/dashboard";
                case RECEPTIONIST -> "redirect:/receptionist/dashboard";
                case HOUSEKEEPING -> "redirect:/housekeeping/dashboard";
                case MAINTENANCE -> "redirect:/maintenance/dashboard";
                default -> "redirect:/home";
            }
        ).orElse(pageName);
    }
}