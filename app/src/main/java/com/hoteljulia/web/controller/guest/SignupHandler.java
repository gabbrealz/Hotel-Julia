package com.hoteljulia.web.controller.guest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteljulia.core.dto.auth.SignupForm;
import com.hoteljulia.core.model.users.Guest;
import com.hoteljulia.core.repository.users.UserRepository;


@Controller
public class SignupHandler {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;

    public SignupHandler(PasswordEncoder passwordEncoder, UserRepository userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }


    @PostMapping("/sign-up")
    public String handleRegister(SignupForm form) {

        if (userRepo.existsByEmail(form.getEmail()))
            return "redirect:/sign-up?error=An+account+already+exists+for+this+email";
        if (!form.validate())
            return "redirect:/sign-up?error=Your+user+information+is+not+valid";

        Guest guest = new Guest();
        guest.setName(form.getName());
        guest.setEmail(form.getEmail());
        guest.setPhoneNumber(form.getPhone());
        guest.setAddress(form.getAddress());

        String hashedPassword = passwordEncoder.encode(form.getPassword());
        guest.setPassword(hashedPassword);

        userRepo.save(guest);
        return "redirect:/";
    }

    @GetMapping(value = "/sign-up/check-email", produces = "application/json")
    @ResponseBody
    public boolean doesEmailExist(@RequestParam("email") String email) {
        return userRepo.existsByEmail(email);
    }
}