package com.learning.trackzilla.controller;

import com.learning.trackzilla.service.ApplicationService;
import com.learning.trackzilla.service.ReleaseService;
import com.learning.trackzilla.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TzaController {
    private final ApplicationService applicationService;
    private final TicketService ticketService;
    private final ReleaseService releaseService;

    public TzaController(ApplicationService applicationService,
            TicketService ticketService,
            ReleaseService releaseService) {
        this.applicationService = applicationService;
        this.ticketService = ticketService;
        this.releaseService = releaseService;
    }

    @GetMapping("/applications")
    public String retrieveApplications(Model model){
        model.addAttribute("applications", applicationService.listApplications());
        return "applications";
    }

    @GetMapping("/tickets")
    public String retrieveTickets(Model model){
        model.addAttribute("tickets", ticketService.listTickets());
        return "tickets";
    }

    @GetMapping("/releases")
    public String retrieveReleases(Model model){
        model.addAttribute("releases", releaseService.listReleases());
        return "releases";
    }
}
