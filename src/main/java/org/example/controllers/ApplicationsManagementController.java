package org.example.controllers;

import org.example.models.Application;
import org.example.models.User;
import org.example.services.impl.ApplicationService;
import org.example.services.impl.UserService;
import org.example.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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

    private int pageN;
    private String sortF;
    private String sortD;
    @Autowired
    public ApplicationsManagementController(UserService userService, ApplicationService applicationService) {
        this.userService = userService;
        this.applicationService = applicationService;
    }

    @GetMapping("/allapps/{pageNum}")
    public String getAllApps(Model model,
                             @PathVariable(name = "pageNum") int pageNum,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir) {
        pageN = pageNum;
        sortF = sortField;
        sortD = sortDir;
        Page<Application> page = applicationService.findAll(pageN, sortF, sortD);
        List<Application> applicationList = page.getContent();


        List<User> servicemanList = userService.findByRole(Role.SERVICEMAN);
        String[] progressArr = new String[]{PROGRESS_NOT_STARTED, PROGRESS_AT_WORK, PROGRESS_DONE};
        String[] paymentList = new String[]{PAYMENT_STATUS_EXPECTED, PAYMENT_STATUS_CANCELED, PAYMENT_STATUS_PAID};

        model.addAttribute("payment", paymentList);
        model.addAttribute("servicemanList", servicemanList);
        model.addAttribute("progressArr", progressArr);

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("applicationList", applicationList);
        return "allApplications";
    }

    @PostMapping("/setPrice")
    public String setPrice(@RequestParam("setPrice") double price, @RequestParam("appId") Application application) {
        application.setPrice(price);
        applicationService.save(application);
        return "redirect:/management/allapps/" + pageN + "?sortField=" + sortF + "&sortDir=" + sortD;
    }

    @PostMapping("/closeApp")
    public String closeApp(@RequestParam("appId") Application application) {
        application.setPaymentStatus(PAYMENT_STATUS_CANCELED);
        applicationService.save(application);
        return "redirect:/management/allapps/" + pageN + "?sortField=" + sortF + "&sortDir=" + sortD;
    }

    @PostMapping("/setServiceman")
    public String setServicemanByManager(@RequestParam("appId") Application application,
                           @RequestParam("setServiceman") User serviceman) {
        application.setServiceman(serviceman);
        applicationService.save(application);
        return "redirect:/management/allapps/" + pageN + "?sortField=" + sortF + "&sortDir=" + sortD;
    }

    @PostMapping("/claimServiceman")
    public String claimServiceman(@RequestParam("appId") Application application,
                                         Model model) {
        User serviceman = (User) model.getAttribute("user");
        System.out.println(serviceman);
        application.setServiceman(serviceman);
        applicationService.save(application);
        return "redirect:/management/allapps/" + pageN + "?sortField=" + sortF + "&sortDir=" + sortD;
    }

    @PostMapping("/changeProgress")
    public String changeProgress(
            @RequestParam("appId") Application application,
            @RequestParam("changeProgress") String changeProgress) {
        application.setProgress(changeProgress);
        applicationService.save(application);
        return "redirect:/management/allapps/" + pageN + "?sortField=" + sortF + "&sortDir=" + sortD;
    }
}
