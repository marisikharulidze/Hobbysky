package com.example.hobbysky;

import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.mapper.UserMapper;
import com.example.hobbysky.model.Event;
import com.example.hobbysky.model.Role;
import com.example.hobbysky.model.User;
import com.example.hobbysky.repository.EventRepository;
import com.example.hobbysky.repository.UserRepository;
import com.example.hobbysky.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser_withDuplicateEmail() {
        UserDTO existingUserDTO = new UserDTO();
        existingUserDTO.setId(1L);
        existingUserDTO.setEmail("existing@example.com");

        UserDTO newUserDTO = new UserDTO();
        newUserDTO.setEmail("existing@example.com");

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setEmail("existing@example.com");
        when(userMapper.userDTOToUser(newUserDTO)).thenReturn(existingUser);
        when(userRepository.findByEmail("existing@example.com")).thenReturn(existingUser);

        assertThrows(RuntimeException.class, () -> userService.saveUser(newUserDTO));
    }

    @Test
    public void testFindAll() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.userToUserDTO(any(User.class))).thenReturn(new UserDTO());

        List<UserDTO> result = userService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindUserById_withExistingId() {
        Long userId = 1L;
        UserDTO expectedUserDTO = new UserDTO();
        expectedUserDTO.setId(userId);

        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.userToUserDTO(user)).thenReturn(expectedUserDTO);

        UserDTO result = userService.findUserById(userId);
        assertEquals(userId, result.getId());
    }

    @Test
    public void testFindUserById_withNonExistingId() {
        Long nonExistingId = 999L;
        when(userRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.findUserById(nonExistingId));
    }

    @Test
    public void testSearchPeople_withValidSearchQuery() {
        String searchQuery = "John";
        List<User> users = Collections.singletonList(new User());
        when(userRepository.findByFirstNameContainingOrLastNameContaining(searchQuery, searchQuery)).thenReturn(users);
        when(userMapper.userToUserDTO(any(User.class))).thenReturn(new UserDTO());
        List<UserDTO> result = userService.searchPeople(searchQuery);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetCurrentUser_whenNotAuthenticated() {
        SecurityContextHolder.clearContext();
        UserDTO result = userService.getCurrentUser();
        assertNull(result);
    }


    @Test
    public void testFindUserById_UserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.findUserById(userId));
    }


    @Test
    public void testGetCurrentUser_NotAuthenticated() {
        SecurityContextHolder.clearContext();
        UserDTO result = userService.getCurrentUser();
        assertNull(result);
    }



    @Test
    public void testValidateUniqueEmail_EmailNotInUse() {
        // Arrange
        Long userId = 1L;
        String email = "john.doe@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);
        userService.validateUniqueEmail(email, userId);
    }

    @Test
    public void testFindUserByEmail_UserNotFound() {
        String email = "john.doe@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userService.findUserByEmail(email));
    }


    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String email = "john.doe@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(email));
    }

    @Test
    public void testJoinEvent_UserNotFound() {
        Long userId = 1L;
        Long eventId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.joinEvent(userId, eventId));
    }


    @Test
    public void testUnjoinEvent_UserNotFound() {
        Long userId = 1L;
        Long eventId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.unjoinEvent(userId, eventId));
    }

}