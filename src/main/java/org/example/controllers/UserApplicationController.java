package org.example.controllers;

import org.example.models.Application;
import org.example.models.User;
import org.example.services.impl.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class UserApplicationController {
    private final ApplicationService applicationService;

    @Autowired
    public UserApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/myapps")
    public String getApplications(Model model) {
        User user = (User) model.getAttribute("user");
        List<Application> applicationList = applicationService.findAllUserApplications(user);
        model.addAttribute("applicationList", applicationList);

        return "userApplications";
    }

    @PostMapping("/add")
    public String addApplication(Model model, @RequestParam("applicationText") String text) {
        User user = (User) model.getAttribute("user");
        applicationService.newApp(text, user);
        return "redirect:/myapps";
    }

    @PostMapping("/response")
    public String addResponse() {

        return "redirect:/myapps";
    }

    @PostMapping("/pay")
    public String pay() {

        return "redirect:/myapps";
    }

}
