package com.example.hobbysky;

import com.example.hobbysky.controller.ProfileController;
import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetUserProfile() {
        Long userId = 1L;
        UserDTO mockUserDTO = new UserDTO();
        mockUserDTO.setId(userId);
        when(userService.findUserById(userId)).thenReturn(mockUserDTO);

        String viewName = profileController.getUserProfile(userId, model);

        verify(userService, times(1)).findUserById(userId);
        verify(model, times(1)).addAttribute(eq("user"), any(UserDTO.class));
        assertEquals("Profile", viewName);
    }

    @Test
    public void testGetUserProfile_ValidUser() {
        Long userId = 1L;
        UserDTO mockUserDTO = new UserDTO();
        mockUserDTO.setId(userId);
        when(userService.findUserById(userId)).thenReturn(mockUserDTO);

        String viewName = profileController.getUserProfile(userId, model);

        verify(userService, times(1)).findUserById(userId);
        verify(model, times(1)).addAttribute(eq("user"), any(UserDTO.class));
        assertEquals("Profile", viewName);
    }

    @Test
    public void testUpdateProfile() {
        UserDTO userDTO = new UserDTO();

        String viewName = profileController.updateProfile(userDTO);
        assertEquals("redirect:/Profile", viewName);

        verify(userService, times(1)).updateUser(userDTO);
    }


    @Test
    public void testGetCurrentUserDTONotUserDetails() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn("notUserDetails");

        UserDTO userDTO = profileController.getCurrentUserDTO();
        assertEquals(null, userDTO);

        verify(userService, times(0)).findUserByEmail(anyString());
    }




}
