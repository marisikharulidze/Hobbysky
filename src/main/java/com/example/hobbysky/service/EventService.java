package com.example.hobbysky.service;

import com.example.hobbysky.dto.EventDTO;
import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.mapper.EventMapper;
import com.example.hobbysky.model.Event;
import com.example.hobbysky.model.User;
import com.example.hobbysky.repository.EventRepository;
import com.example.hobbysky.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, EventMapper eventMapper, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.userRepository = userRepository;
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::eventToEventDTO)
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Long id) {
        return eventRepository.findById(id)
                .map(eventMapper::eventToEventDTO)
                .orElse(null);
    }

    public List<EventDTO> searchEvents(String query) {
        List<Event> events = eventRepository.findByNameContainingIgnoreCase(query);
        return events.stream()
                .map(eventMapper::eventToEventDTO)
                .collect(Collectors.toList());
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = eventMapper.eventDTOToEvent(eventDTO);
        event = eventRepository.save(event);
        return eventMapper.eventToEventDTO(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public void addUserToEvent(Long eventId, UserDTO userDTO) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        event.getUsers().add(user);
        eventRepository.save(event);
    }

    public void removeUserFromEvent(Long eventId, UserDTO userDTO) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        User user = userRepository.findById(userDTO.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        event.getUsers().remove(user);
        eventRepository.save(event);
    }
}
