package org.example.controllers;

import org.example.dto.UserDTO;
import org.example.services.impl.AuthService;
import org.example.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAuthority('MANAGER')")
@RequestMapping("/admin")
public class AdminController {
    private final AuthService authService;

    @Autowired
    public AdminController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/create")
    public String getCreationPage() {
        return "createUser";
    }

    @PostMapping("/create")
    public String createUser(@Valid UserDTO userDTO, @RequestParam("role") Role role, Model model) {

        if (!authService.register(userDTO, role)) {
            model.addAttribute("usernameError", "User exists!");
            return "createUser";
        }

        return "redirect:/user/profile";
    }
}
