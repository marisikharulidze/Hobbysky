package com.example.hobbysky;

import com.example.hobbysky.controller.AdministrationController;
import com.example.hobbysky.dto.ProfileDTO;
import com.example.hobbysky.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdministrationControllerTests {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private AdministrationController administrationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(administrationController).build();
    }


    @Test
    public void testGetProfileById() throws Exception {
        Long profileId = 1L;
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profileId);
        when(profileService.findById(profileId)).thenReturn(profileDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/profile/{id}", profileId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("profile"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("profile"));

        verify(profileService, times(1)).findById(profileId);
    }


    @Test
    public void testDeleteProfile() throws Exception {
        Long profileId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/profile/{id}", profileId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(profileService, times(1)).deleteProfile(profileId);
    }
}
