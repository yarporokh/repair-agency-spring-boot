package org.example.controllers;

import org.example.models.Application;
import org.example.models.User;
import org.example.services.impl.ApplicationService;
import org.example.services.impl.UserService;
import org.example.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.utils.ApplicationConstants.*;

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
        List<User> servicemanList = userService.findByRole(Role.SERVICEMAN);
        String[] progressArr = new String[]{PROGRESS_NOT_STARTED, PROGRESS_AT_WORK, PROGRESS_DONE};
        String[] paymentList = new String[]{PAYMENT_STATUS_EXPECTED, PAYMENT_STATUS_CANCELED, PAYMENT_STATUS_PAID};

        model.addAttribute("payment", paymentList);
        model.addAttribute("servicemanList", servicemanList);
        model.addAttribute("progressArr", progressArr);
        model.addAttribute("applicationList", applicationList);
        return "allApplications";
    }

    @PostMapping("/setPrice")
    public String setPrice(@RequestParam("setPrice") double price, @RequestParam("appId") Application application) {
        application.setPrice(price);
        applicationService.save(application);
        return "redirect:/management/allapps";
    }

    @PostMapping("/closeApp")
    public String closeApp(@RequestParam("appId") Application application) {
        application.setPaymentStatus(PAYMENT_STATUS_CANCELED);
        applicationService.save(application);
        return "redirect:/management/allapps";
    }

    @PostMapping("/setServiceman")
    public String setServicemanByManager(@RequestParam("appId") Application application,
                           @RequestParam("setServiceman") User serviceman) {
        application.setServiceman(serviceman);
        applicationService.save(application);
        return "redirect:/management/allapps";
    }

    @PostMapping("/claimServiceman")
    public String claimServiceman(@RequestParam("appId") Application application,
                                         Model model) {
        User serviceman = (User) model.getAttribute("user");
        System.out.println(serviceman);
        application.setServiceman(serviceman);
        applicationService.save(application);
        return "redirect:/management/allapps";
    }

    @PostMapping("/changeProgress")
    public String changeProgress(
            @RequestParam("appId") Application application,
            @RequestParam("changeProgress") String changeProgress) {
        application.setProgress(changeProgress);
        applicationService.save(application);
        return "redirect:/management/allapps";
    }
}
