package com.example.hobbysky.controller;

import org.springframework.ui.Model;
import com.example.hobbysky.dto.ProfileDTO;
import com.example.hobbysky.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdministrationController {
    private final ProfileService profileService;

    @Autowired
    public AdministrationController(ProfileService profileService) {
        this.profileService = profileService;
    }

    //PROFILE
    @GetMapping("/profiles")
    public List<ProfileDTO> getAllProfiles() {
        return profileService.findAll();
    }

    @GetMapping("/profile/{id}")
    public String getProfileById(@PathVariable Long id, Model model) {
        ProfileDTO profileDTO = profileService.findById(id);
        model.addAttribute("profile", profileDTO); // Add profileDTO to the model
        return "profile"; // Return the name of your Thymeleaf template (e.g., "profile")
    }

    @PostMapping("/addProfile")
    public ProfileDTO createProfile(@RequestBody ProfileDTO profileDTO) {
        return profileService.saveProfile(profileDTO);
    }
//
//    @PutMapping("/profile/{id}")
//    public ProfileDTO updateProfile(@PathVariable Long id, @RequestBody ProfileDTO profileDTO) {
//        return profileService.updateProfile(id, profileDTO);
//    }

    @DeleteMapping("/profile/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
    }

    //LOCATION

}
