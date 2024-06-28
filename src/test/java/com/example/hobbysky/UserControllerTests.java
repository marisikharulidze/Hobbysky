package com.example.hobbysky;

import com.example.hobbysky.controller.UserController;
import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPeople() {
        List<UserDTO> mockPeople = new ArrayList<>();
        when(userService.findAll()).thenReturn(mockPeople);

        String viewName = userController.people(null, model);
        verify(userService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("people"), any());
        assert "People".equals(viewName);
    }

    @Test
    public void testUpdateUser() {
        UserDTO userDTO = new UserDTO();
        String viewName = userController.updateUser(userDTO);
        assertEquals("redirect:/Profile", viewName);

        verify(userService, times(1)).updateUser(userDTO);
    }

    @Test
    public void testRegisterPost() {
        UserDTO userDTO = new UserDTO();
        String viewName = userController.registerUser(userDTO);
        assertEquals("redirect:/HomePage", viewName);

        verify(userService, times(1)).saveUser(userDTO);
    }

    @Test
    public void testRegisterGet() {
        String viewName = userController.login(model);
        assertEquals("Register", viewName);

        verify(model, times(1)).addAttribute(eq("userDTO"), any(UserDTO.class));
    }

    @Test
    public void testSettings() {
        UserDTO userDTO = new UserDTO();
        when(userService.getCurrentUser()).thenReturn(userDTO);

        String viewName = userController.settings(model);
        assertEquals("Settings", viewName);

        verify(userService, times(1)).getCurrentUser();
        verify(model, times(1)).addAttribute("userDTO", userDTO);
    }

    @Test
    public void testPeopleWithSearch() {
        List<UserDTO> userDTOList = Arrays.asList(new UserDTO(), new UserDTO());
        when(userService.searchPeople("searchQuery")).thenReturn(userDTOList);

        String viewName = userController.people("searchQuery", model);
        assertEquals("People", viewName);

        verify(userService, times(1)).searchPeople("searchQuery");
        verify(model, times(1)).addAttribute("people", userDTOList);
    }

    @Test
    public void testPeopleWithoutSearch() {
        List<UserDTO> userDTOList = Arrays.asList(new UserDTO(), new UserDTO());
        when(userService.findAll()).thenReturn(userDTOList);

        String viewName = userController.people(null, model);
        assertEquals("People", viewName);

        verify(userService, times(1)).findAll();
        verify(model, times(1)).addAttribute("people", userDTOList);
    }

    @Test
    public void testLogOut() {
        String viewName = userController.logOut(model);
        assertEquals("redirect:/login", viewName);
    }
}
