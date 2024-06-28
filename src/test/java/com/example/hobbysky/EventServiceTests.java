package com.example.hobbysky;
import com.example.hobbysky.dto.EventDTO;
import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.mapper.EventMapper;
import com.example.hobbysky.model.Event;
import com.example.hobbysky.model.User;
import com.example.hobbysky.repository.EventRepository;
import com.example.hobbysky.repository.UserRepository;
import com.example.hobbysky.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EventServiceTests {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventMapper eventMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEvents() {
        Event event1 = new Event();
        Event event2 = new Event();
        when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2));

        EventDTO eventDTO1 = new EventDTO();
        EventDTO eventDTO2 = new EventDTO();
        when(eventMapper.eventToEventDTO(event1)).thenReturn(eventDTO1);
        when(eventMapper.eventToEventDTO(event2)).thenReturn(eventDTO2);

        List<EventDTO> events = eventService.getAllEvents();

        assertEquals(2, events.size());
        assertEquals(eventDTO1, events.get(0));
        assertEquals(eventDTO2, events.get(1));
    }

    @Test
    public void testGetEventById_existingId() {
        Long eventId = 1L;
        Event event = new Event();
        event.setId(eventId);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventId);
        when(eventMapper.eventToEventDTO(event)).thenReturn(eventDTO);
        EventDTO retrievedEvent = eventService.getEventById(eventId);

        assertNotNull(retrievedEvent);
        assertEquals(eventId, retrievedEvent.getId());
    }

    @Test
    public void testGetEventById_nonExistingId() {
        Long nonExistingId = 999L;
        when(eventRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        EventDTO retrievedEvent = eventService.getEventById(nonExistingId);

        assertNull(retrievedEvent);
    }

    @Test
    public void testDeleteEvent_withNonExistingId() {
        Long nonExistingId = 999L;
        doThrow(new RuntimeException("Event not found")).when(eventRepository).deleteById(nonExistingId);
        assertThrows(RuntimeException.class, () -> eventService.deleteEvent(nonExistingId));
    }

    @Test
    public void testAddUserToEvent_withNonExistingEventOrUser() {
        Long eventId = 1L;
        Long userId = 1L;
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventId);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> eventService.addUserToEvent(eventId, userDTO));
    }

    @Test
    public void testRemoveUserFromEvent_withNonExistingEventOrUser() {
        Long eventId = 1L;
        Long userId = 1L;
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventId);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> eventService.removeUserFromEvent(eventId, userDTO));
    }


}
