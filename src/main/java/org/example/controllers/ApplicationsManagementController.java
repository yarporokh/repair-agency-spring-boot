package org.example.controllers;

import org.example.models.Application;
import org.example.services.impl.ApplicationService;
import org.example.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('MANAGER', 'SERVICEMAN')")
@RequestMapping("/management")
public class ApplicationsManagementController {
    private final UserService userService;
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationsManagementController(UserService userService, ApplicationService applicationService) {
        this.userService = userService;
        this.applicationService = applicationService;
    }

    @GetMapping("/allapps")
    public String getAllApps(Model model) {
        List<Application> applicationList = applicationService.findAll();
        model.addAttribute("applicationList", applicationList);
        return "allApplications";
    }
}
