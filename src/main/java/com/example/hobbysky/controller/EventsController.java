package com.example.hobbysky.controller;

import com.example.hobbysky.dto.EventDTO;
import com.example.hobbysky.dto.HobbyDTO;
import com.example.hobbysky.dto.LocationDTO;
import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.mapper.EventMapper;
import com.example.hobbysky.model.Event;
import com.example.hobbysky.service.EventService;
import com.example.hobbysky.service.HobbyService;
import com.example.hobbysky.service.LocationService;
import com.example.hobbysky.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
public class EventsController {
    private final EventService eventService;
    private final HobbyService hobbyService;
    private final UserService userService;
    private final LocationService locationService;
    private final EventMapper eventMapper;

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    public EventsController(EventService eventService, HobbyService hobbyService, UserService userService, LocationService locationService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.hobbyService = hobbyService;
        this.userService = userService;
        this.locationService = locationService;
        this.eventMapper = eventMapper;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/Events")
    public String getEvents(@RequestParam(name = "search", required = false) String search, Model model) {
        List<EventDTO> events;
        if (search != null && !search.isEmpty()) {
            events = eventService.searchEvents(search);
        } else {
            events = eventService.getAllEvents();
        }
        UserDTO currentUser = userService.getCurrentUser();
        model.addAttribute("events", events);
        model.addAttribute("currentUser", currentUser);
        return "Events";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/Event/{id}")
    public String event(@PathVariable Long id, Model model) {
        EventDTO event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "Event";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/AddEvent")
    public String showAddEventForm(Model model) {
        model.addAttribute("event", new EventDTO());
        return "AddEvent";
    }

    //    @PreAuthorize("hasRole('USER')")
    @PostMapping("/AddEvent")
    @PreAuthorize("permitAll()")
    public String addEvent(@ModelAttribute EventDTO eventDTO, Model model) {
        eventService.createEvent(eventDTO);
        model.addAttribute("event", eventDTO);
        return "redirect:/Events";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/api/hobbies")
    @ResponseBody
    public List<HobbyDTO> getHobbies() {
        return hobbyService.findAll();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/api/locations")
    @ResponseBody
    public List<LocationDTO> getLocations() {
        return locationService.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/joinEvent")
    public String joinEvent(@RequestParam("eventId") Long eventId) {
        UserDTO currentUser = userService.getCurrentUser();
        eventService.addUserToEvent(eventId, currentUser);
        return "redirect:/Events";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/unjoinEvent")
    public String unjoinEvent(@RequestParam("eventId") Long eventId) {
        UserDTO currentUser = userService.getCurrentUser();
        eventService.removeUserFromEvent(eventId, currentUser);
        return "redirect:/Events";
    }

}
