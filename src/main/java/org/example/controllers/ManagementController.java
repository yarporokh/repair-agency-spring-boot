package org.example.controllers;

import org.example.models.User;
import org.example.services.impl.UserService;
import org.example.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('MANAGER')")
@RequestMapping("/management")
public class ManagementController {
    private final UserService userService;

    @Autowired
    public ManagementController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<User> userList = userService.findByRole(Role.USER);
        model.addAttribute("list", userList);
        return "allUsers";
    }

    @PostMapping("/balance")
    public String addNew(@RequestParam("newBalance") double newBalance, @RequestParam("itemId") User item) {
        userService.saveNewUserBalance(item, newBalance);
        return "redirect:/management/users";
    }
}
