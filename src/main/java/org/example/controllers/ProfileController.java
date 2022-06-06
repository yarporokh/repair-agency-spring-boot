package org.example.controllers;

import org.example.models.User;
import org.example.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/balance")
    public String getBalancePage() {
        return "balance";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/balance")
    public String topUpBalance(@AuthenticationPrincipal User user, @RequestParam("addBalance") double balance) {
        System.out.println(user);
        System.out.println(balance);
//        userService.saveUserBalance(user, balance);
        return "redirect:/user/profile";
    }
}
