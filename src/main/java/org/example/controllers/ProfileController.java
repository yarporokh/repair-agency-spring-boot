package org.example.controllers;

import org.example.models.User;
import org.example.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String topUpBalance(Model model, @RequestParam("addBalance") double balance) {
        User user = (User) model.getAttribute("user");
        assert user != null;
        userService.saveUserBalance(user, balance);
        return "redirect:/user/profile";
    }
}
