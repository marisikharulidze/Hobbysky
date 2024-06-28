package com.example.hobbysky.controller;

import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/People")
    public String people(@RequestParam(required = false) String search, Model model) {
        List<UserDTO> people;
        if (search != null && !search.isEmpty()) {
            people = userService.searchPeople(search);
        } else {
            people = userService.findAll();
        }
        model.addAttribute("people", people);
        return "People";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/Settings")
    public String settings(Model model) {
        UserDTO userDTO = userService.getCurrentUser();
        model.addAttribute("userDTO", userDTO);
        return "Settings";
    }


    @PreAuthorize("permitAll()")
    @GetMapping("/Register")
    public String login(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "Register";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/Register")
    public String registerUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "redirect:/HomePage";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/Settings")
    public String updateUser(@ModelAttribute UserDTO userDTO) {
        userService.updateUser(userDTO);
        return "redirect:/Profile";
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/LogOut")
    public String logOut(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "redirect:/login";
    }

}
