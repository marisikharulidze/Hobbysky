package com.example.hobbysky;

import com.example.hobbysky.controller.EventsController;
import com.example.hobbysky.dto.EventDTO;
import com.example.hobbysky.dto.HobbyDTO;
import com.example.hobbysky.dto.LocationDTO;
import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.service.EventService;
import com.example.hobbysky.service.HobbyService;
import com.example.hobbysky.service.LocationService;
import com.example.hobbysky.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class EventsControllerTests {

    @Mock
    private EventService eventService;

    @Mock
    private HobbyService hobbyService;

    @Mock
    private UserService userService;

    @Mock
    private LocationService locationService;

    @InjectMocks
    private EventsController eventsController;

    @Mock
    private Model model;

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddEventWithEmptyFile() {
        EventDTO eventDTO = new EventDTO();
        MultipartFile file = new MockMultipartFile("image", "", "", new byte[0]);

        String viewName = eventsController.addEvent(eventDTO, file);

        verify(eventService, times(1)).createEvent(eventDTO);

        assertEquals("redirect:/Events", viewName);
        assertNull(eventDTO.getImage(), "Image path should be null when file is empty.");
    }

    @Test
    public void testGetHobbies() {
        List<HobbyDTO> mockHobbies = new ArrayList<>();
        mockHobbies.add(new HobbyDTO("1", "Reading"));
        mockHobbies.add(new HobbyDTO("2", "Gaming"));
        when(hobbyService.findAll()).thenReturn(mockHobbies);

        List<HobbyDTO> result = eventsController.getHobbies();
        verify(hobbyService, times(1)).findAll();

        assertEquals(mockHobbies.size(), result.size());
        assertEquals(mockHobbies.get(0).getName(), result.get(0).getName());
        assertEquals(mockHobbies.get(1).getName(), result.get(1).getName());
    }

    @Test
    public void testGetLocations() {
        List<LocationDTO> mockLocations = new ArrayList<>();
        mockLocations.add(new LocationDTO("1", "USA", "New York"));
        mockLocations.add(new LocationDTO("2", "Canada", "Toronto"));
        when(locationService.findAll()).thenReturn(mockLocations);

        List<LocationDTO> result = eventsController.getLocations();

        verify(locationService, times(1)).findAll();

        assertEquals(mockLocations.size(), result.size());
        assertEquals(mockLocations.get(0).getCity(), result.get(0).getCity());
        assertEquals(mockLocations.get(1).getCity(), result.get(1).getCity());
    }

    @Test
    public void testJoinEvent() {
        Long eventId = 1L;
        UserDTO mockUser = new UserDTO();
        when(userService.getCurrentUser()).thenReturn(mockUser);

        String viewName = eventsController.joinEvent(eventId);
        verify(eventService, times(1)).addUserToEvent(eventId, mockUser);
        assertEquals("redirect:/Events", viewName);
    }

    @Test
    public void testUnjoinEvent() {
        Long eventId = 1L;
        UserDTO mockUser = new UserDTO();
        when(userService.getCurrentUser()).thenReturn(mockUser);

        String viewName = eventsController.unjoinEvent(eventId);

        verify(eventService, times(1)).removeUserFromEvent(eventId, mockUser);
        assertEquals("redirect:/Events", viewName);
    }


    @Test
    public void testGetEvents() {
        List<EventDTO> mockEvents = new ArrayList<>();
        when(eventService.getAllEvents()).thenReturn(mockEvents);
        when(userService.getCurrentUser()).thenReturn(new UserDTO());
        String viewName = eventsController.getEvents(null, model);

        verify(eventService, times(1)).getAllEvents();
        verify(userService, times(1)).getCurrentUser();

        verify(model, times(1)).addAttribute(eq("events"), any());
        verify(model, times(1)).addAttribute(eq("currentUser"), any());

        assertEquals("Events", viewName);
    }

    @Test
    public void testEvent() {
        Long eventId = 1L;
        EventDTO mockEvent = new EventDTO();
        when(eventService.getEventById(eventId)).thenReturn(mockEvent);

        String viewName = eventsController.event(eventId, model);
        verify(eventService, times(1)).getEventById(eventId);
        verify(model, times(1)).addAttribute(eq("event"), any());

        assertEquals("Event", viewName);
    }

}
