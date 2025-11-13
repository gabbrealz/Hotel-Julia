package com.hoteljulia.web.controller.guest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GuestController {
    
    @GetMapping("/home")
    public String homePage() {
        return "guest/home";
    }   
}
