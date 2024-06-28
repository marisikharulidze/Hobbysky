package com.example.hobbysky;

import com.example.hobbysky.dto.EventDTO;
import com.example.hobbysky.dto.HobbyDTO;
import com.example.hobbysky.dto.LocationDTO;
import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.model.Event;
import com.example.hobbysky.model.Hobby;
import com.example.hobbysky.model.Location;
import com.example.hobbysky.model.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EventTests {

    @Test
    public void testGetId() {
        Event event = new Event();
        event.setId(1L);
        assertEquals(1L, event.getId());
    }

    @Test
    public void testSetId() {
        Event event = new Event();
        event.setId(2L);
        assertEquals(2L, event.getId());
    }

    @Test
    public void testGetName() {
        Event event = new Event();
        event.setName("Concert");
        assertEquals("Concert", event.getName());
    }

    @Test
    public void testSetName() {
        Event event = new Event();
        event.setName("Workshop");
        assertEquals("Workshop", event.getName());
    }

    @Test
    public void testGetDate() {
        Date now = new Date();
        Event event = new Event();
        event.setDate(now);
        assertEquals(now, event.getDate());
    }

    @Test
    public void testSetDate() {
        Date now = new Date();
        Event event = new Event();
        event.setDate(now);
        assertEquals(now, event.getDate());
    }

    @Test
    public void testGetLocation() {
        Location location = new Location();
        location.setId(1L);
        location.setCountry("USA");
        location.setCity("New York");

        Event event = new Event();
        event.setLocation(location);

        assertEquals(location, event.getLocation());
    }

    @Test
    public void testSetLocation() {
        Location location = new Location();
        location.setId(2L);
        location.setCountry("Canada");
        location.setCity("Toronto");

        Event event = new Event();
        event.setLocation(location);

        assertEquals(location, event.getLocation());
    }

    @Test
    public void testGetDescription() {
        Event event = new Event();
        event.setDescription("An exciting event.");
        assertEquals("An exciting event.", event.getDescription());
    }

    @Test
    public void testSetDescription() {
        Event event = new Event();
        event.setDescription("A wonderful workshop.");
        assertEquals("A wonderful workshop.", event.getDescription());
    }

    @Test
    public void testGetHobby() {
        Hobby hobby = new Hobby();
        hobby.setId(1L);
        hobby.setName("Music");

        Event event = new Event();
        event.setHobby(hobby);

        assertEquals(hobby, event.getHobby());
    }

    @Test
    public void testSetHobby() {
        Hobby hobby = new Hobby();
        hobby.setId(2L);
        hobby.setName("Art");

        Event event = new Event();
        event.setHobby(hobby);

        assertEquals(hobby, event.getHobby());
    }

    @Test
    public void testGetNumOfParticipants() {
        Event event = new Event();
        event.setNumOfParticipants(100);
        assertEquals(100, event.getNumOfParticipants());
    }

    @Test
    public void testSetNumOfParticipants() {
        Event event = new Event();
        event.setNumOfParticipants(200);
        assertEquals(200, event.getNumOfParticipants());
    }

    @Test
    public void testGetStatus() {
        Event event = new Event();
        event.setStatus("Scheduled");
        assertEquals("Scheduled", event.getStatus());
    }

    @Test
    public void testSetStatus() {
        Event event = new Event();
        event.setStatus("Completed");
        assertEquals("Completed", event.getStatus());
    }

    @Test
    public void testGetImage() {
        Event event = new Event();
        event.setImage("image.jpg");
        assertEquals("image.jpg", event.getImage());
    }

    @Test
    public void testSetImage() {
        Event event = new Event();
        event.setImage("new_image.jpg");
        assertEquals("new_image.jpg", event.getImage());
    }

    @Test
    public void testGetCreationDate() {
        Date now = new Date();
        Event event = new Event();
        event.setCreationDate(now);
        assertEquals(now, event.getCreationDate());
    }

    @Test
    public void testSetCreationDate() {
        Date now = new Date();
        Event event = new Event();
        event.setCreationDate(now);
        assertEquals(now, event.getCreationDate());
    }

    @Test
    public void testGetLastModifiedDate() {
        Date now = new Date();
        Event event = new Event();
        event.setLastModifiedDate(now);
        assertEquals(now, event.getLastModifiedDate());
    }

    @Test
    public void testSetLastModifiedDate() {
        Date now = new Date();
        Event event = new Event();
        event.setLastModifiedDate(now);
        assertEquals(now, event.getLastModifiedDate());
    }

    @Test
    public void testGetUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("John");
        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Jane");

        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);

        Event event = new Event();
        event.setUsers(users);

        assertEquals(users, event.getUsers());
    }
    @Test
    public void testEmptyConstructor() {
        EventDTO eventDTO = new EventDTO();
        assertNotNull(eventDTO);
    }

    @Test
    public void testGetterAndSetterMethods() {
        EventDTO eventDTO = new EventDTO();

        Long id = 1L;
        String name = "Test Event";
        Date date = new Date();
        LocationDTO location = new LocationDTO();
        String description = "Test Description";
        HobbyDTO hobby = new HobbyDTO();
        Integer numOfParticipants = 50;
        String status = "Scheduled";
        String image = "test_image.jpg";
        Date creationDate = new Date();
        Date lastModifiedDate = new Date();
        Set<UserDTO> users = new HashSet<>();

        eventDTO.setId(id);
        eventDTO.setName(name);
        eventDTO.setDate(date);
        eventDTO.setLocation(location);
        eventDTO.setDescription(description);
        eventDTO.setHobby(hobby);
        eventDTO.setNumOfParticipants(numOfParticipants);
        eventDTO.setStatus(status);
        eventDTO.setImage(image);
        eventDTO.setCreationDate(creationDate);
        eventDTO.setLastModifiedDate(lastModifiedDate);
        eventDTO.setUsers(users);

        assertEquals(id, eventDTO.getId());
        assertEquals(name, eventDTO.getName());
        assertEquals(date, eventDTO.getDate());
        assertEquals(location, eventDTO.getLocation());
        assertEquals(description, eventDTO.getDescription());
        assertEquals(hobby, eventDTO.getHobby());
        assertEquals(numOfParticipants, eventDTO.getNumOfParticipants());
        assertEquals(status, eventDTO.getStatus());
        assertEquals(image, eventDTO.getImage());
        assertEquals(creationDate, eventDTO.getCreationDate());
        assertEquals(lastModifiedDate, eventDTO.getLastModifiedDate());
        assertEquals(users, eventDTO.getUsers());
    }

    @Test
    public void testSetUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Alice");

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Bob");
        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);

        Event event = new Event();
        event.setUsers(users);
        assertEquals(users, event.getUsers());
    }

    @Test
    public void testConstructorWithParameters() {
        Long id = 1L;
        String name = "Test Event";
        Date date = new Date();
        Location location = new Location();
        String description = "Test Description";
        Hobby hobby = new Hobby();
        Integer numOfParticipants = 50;
        String status = "Scheduled";
        String image = "test_image.jpg";
        Date creationDate = new Date();
        Date lastModifiedDate = new Date();
        Set<User> users = new HashSet<>();
        Event event = new Event(id, name, date, location, description, hobby, numOfParticipants, status, image, creationDate, lastModifiedDate, users);

        assertEquals(id, event.getId());
        assertEquals(name, event.getName());
        assertEquals(date, event.getDate());
        assertEquals(location, event.getLocation());
        assertEquals(description, event.getDescription());
        assertEquals(hobby, event.getHobby());
        assertEquals(numOfParticipants, event.getNumOfParticipants());
        assertEquals(status, event.getStatus());
        assertEquals(image, event.getImage());
        assertEquals(creationDate, event.getCreationDate());
        assertEquals(lastModifiedDate, event.getLastModifiedDate());
        assertEquals(users, event.getUsers());
    }


}
