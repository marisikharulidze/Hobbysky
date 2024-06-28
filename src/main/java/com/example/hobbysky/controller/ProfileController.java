package com.example.hobbysky.controller;

import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.model.User;
import com.example.hobbysky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class ProfileController {

    private UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/Profile/{id}")
    public String getUserProfile(@PathVariable Long id, Model model) {
        UserDTO user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "Profile";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute("user") UserDTO userDTO) {
        userService.updateUser(userDTO);
        return "redirect:/Profile"; // Redirect to settings page after update
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/Profile")
    public String getCurrentUserProfile(Model model) {
        UserDTO user = getCurrentUserDTO();
        model.addAttribute("user", user);
        return "Profile";
    }

    public UserDTO getCurrentUserDTO() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userService.findUserByEmail(username);
        }
        return null;
    }
}