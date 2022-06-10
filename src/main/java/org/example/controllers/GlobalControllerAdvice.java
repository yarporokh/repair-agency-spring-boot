package org.example.controllers;

import org.example.models.User;
import org.example.utils.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import static org.example.utils.ApplicationConstants.*;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("user")
    public User userModel() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (User) authentication.getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    @ModelAttribute("roles")
    public Role[] rolesModel() {
        return Role.values();
    }
}
