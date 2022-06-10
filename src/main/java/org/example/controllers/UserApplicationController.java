package org.example.controllers;

import org.example.models.Application;
import org.example.models.User;
import org.example.services.impl.ApplicationService;
import org.example.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.example.utils.ApplicationConstants.*;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class UserApplicationController {
    private final ApplicationService applicationService;
    private final UserService userService;

    @Autowired
    public UserApplicationController(ApplicationService applicationService, UserService userService) {
        this.applicationService = applicationService;
        this.userService = userService;
    }

    @GetMapping("/myapps")
    public String getApplications(Model model) {
        User user = (User) model.getAttribute("user");
        List<Application> applicationList = applicationService.findAllUserApplications(user);
        model.addAttribute("applicationList", applicationList);
        model.addAttribute("expected", PAYMENT_STATUS_EXPECTED);
        model.addAttribute("done", PROGRESS_DONE);
        return "userApplications";
    }

    @PostMapping("/add")
    public String addApplication(Model model, @RequestParam("applicationText") String text) {
        User user = (User) model.getAttribute("user");
        applicationService.newApp(text, user);
        return "redirect:/myapps";
    }

    @PostMapping("/response")
    public String addResponse(@RequestParam("appId") Application application,
                              @RequestParam("responseText") String responseText) {
        application.setResponseText(responseText);
        applicationService.save(application);
        return "redirect:/myapps";
    }

    @PostMapping("/pay")
    public String pay(Model model,
                      @RequestParam("appId") Application application) {
        User user = (User) model.getAttribute("user");

        assert user != null;
        if (user.getBalance() < application.getPrice())
            return "redirect:/user/balance";



        application.setPaymentStatus(PAYMENT_STATUS_PAID);
        applicationService.save(application);


        return "redirect:/myapps";
    }

}
