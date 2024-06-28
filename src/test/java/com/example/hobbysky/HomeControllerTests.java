package com.example.hobbysky;

import com.example.hobbysky.controller.HomeController;
import com.example.hobbysky.dto.HobbyDTO;
import com.example.hobbysky.service.HobbyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HomeControllerTests {

    @Mock
    private HobbyService hobbyService;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHomePage() {
        String viewName = homeController.homePage();
        assertEquals("HomePage", viewName);
    }

    @Test
    public void testPricing() {
        String viewName = homeController.pricing();
        assertEquals("Pricing", viewName);
    }

    @Test
    public void testIndex() {
        List<HobbyDTO> hobbies = new ArrayList<>();
        when(hobbyService.findAll()).thenReturn(hobbies);

        String viewName = homeController.index(model);
        assertEquals("HomePage", viewName);

        verify(model, times(1)).addAttribute("hobbies", hobbies);
        verify(model, times(1)).addAttribute("name", "World");
        verify(hobbyService, times(1)).findAll();
    }
}
