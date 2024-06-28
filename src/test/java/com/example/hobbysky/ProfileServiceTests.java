package com.example.hobbysky;

import com.example.hobbysky.dto.ProfileDTO;
import com.example.hobbysky.mapper.ProfileMapper;
import com.example.hobbysky.mapper.UserMapper;
import com.example.hobbysky.model.Profile;
import com.example.hobbysky.model.User;
import com.example.hobbysky.repository.ProfileRepository;
import com.example.hobbysky.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfileServiceTests {

    private ProfileRepository profileRepository;
    private ProfileService profileService;
    private ProfileMapper profileMapper = ProfileMapper.INSTANCE;
    private UserMapper userMapper = UserMapper.INSTANCE;

    @BeforeEach
    public void setUp() {
        profileRepository = mock(ProfileRepository.class);
        profileService = new ProfileService(profileRepository);
    }

    @Test
    public void testFindAllProfiles() {
        Profile profile1 = new Profile();
        profile1.setId(1L);
        profile1.setDescription("Description 1");

        Profile profile2 = new Profile();
        profile2.setId(2L);
        profile2.setDescription("Description 2");

        when(profileRepository.findAll()).thenReturn(Arrays.asList(profile1, profile2));

        List<ProfileDTO> profileDTOs = profileService.findAll();
        assertEquals(2, profileDTOs.size());
        assertEquals("Description 1", profileDTOs.get(0).getDescription());
        assertEquals("Description 2", profileDTOs.get(1).getDescription());

        verify(profileRepository, times(1)).findAll();
    }


    @Test
    public void testFindProfileById() {
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setDescription("Description 1");

        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));

        ProfileDTO profileDTO = profileService.findById(1L);
        assertNotNull(profileDTO);
        assertEquals(1L, profileDTO.getId());
        assertEquals("Description 1", profileDTO.getDescription());

        verify(profileRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByIdNotFound() {
        when(profileRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            profileService.findById(1L);
        });

        String expectedMessage = "Profile not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(profileRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteProfile() {
        profileService.deleteProfile(1L);
        verify(profileRepository, times(1)).deleteById(1L);
    }

}
