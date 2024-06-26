package com.example.hobbysky.controller;

import com.example.hobbysky.service.HobbyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final HobbyService hobbyService;

    public HomeController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/HomePage")
    public String homePage() {
        return "HomePage";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/Pricing")
    public String pricing() {
        return "Pricing";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hobbies", hobbyService.findAll());
        model.addAttribute("name", "World");
        return "HomePage"; // This refers to src/main/resources/templates/index.html
    }
}
