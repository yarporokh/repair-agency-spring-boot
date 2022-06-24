package org.example.controllers;

import org.example.dto.UserDTO;
import org.example.services.impl.AuthService;
import org.example.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/registration")
    public String getSignPage() {
        return "registration";
    }


    @PostMapping("/registration")
    public String signUp(@RequestParam("passwordConfirm") String passwordConfirm,
                         @Valid UserDTO userDTO,
                         Model model
    ) {

        if (!userDTO.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Passwords are different!");
            return "registration";
        }

        if (!authService.register(userDTO, Role.USER)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }
        return "redirect:/auth/login";
    }

}
