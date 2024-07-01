package com.example.hobbysky.service;

import com.example.hobbysky.dto.EventDTO;
import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.mapper.EventMapper;
import com.example.hobbysky.model.Event;
import com.example.hobbysky.model.Hobby;
import com.example.hobbysky.model.Location;
import com.example.hobbysky.model.User;
import com.example.hobbysky.repository.EventRepository;
import com.example.hobbysky.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

    public void createEvent(EventDTO eventDTO) {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Event event = eventMapper.eventDTOToEvent(eventDTO);
        event.setCreationDate(date);
        event.setLastModifiedDate(date);
        event.setDate(date);
//        event.setImage("none");
//        event.setId(7L);
//		event.setName("smth");
//		event.setLocation(new Location(1L,"Germany", "Berlin"));
//		event.setHobby( new Hobby(7L,"Mountain climbing"));
//		event.setNumOfParticipants(5);
//		event.setStatus("active");
        event = eventRepository.save(event);
        eventMapper.eventToEventDTO(event);
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
