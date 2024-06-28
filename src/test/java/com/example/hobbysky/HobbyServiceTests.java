package com.example.hobbysky;

import com.example.hobbysky.dto.HobbyDTO;
import com.example.hobbysky.mapper.HobbyMapper;
import com.example.hobbysky.model.Hobby;
import com.example.hobbysky.repository.HobbyRepository;
import com.example.hobbysky.service.HobbyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HobbyServiceTests {

    private HobbyRepository hobbyRepository;
    private HobbyService hobbyService;
    private HobbyMapper hobbyMapper = HobbyMapper.INSTANCE;

    @BeforeEach
    public void setUp() {
        hobbyRepository = mock(HobbyRepository.class);
        hobbyService = new HobbyService(hobbyRepository);
    }

    @Test
    public void testFindAll() {
        Hobby hobby1 = new Hobby();
        hobby1.setId(1L);
        hobby1.setName("Reading");

        Hobby hobby2 = new Hobby();
        hobby2.setId(2L);
        hobby2.setName("Writing");

        when(hobbyRepository.findAll()).thenReturn(Arrays.asList(hobby1, hobby2));

        List<HobbyDTO> hobbyDTOs = hobbyService.findAll();
        assertEquals(2, hobbyDTOs.size());
        assertEquals("Reading", hobbyDTOs.get(0).getName());
        assertEquals("Writing", hobbyDTOs.get(1).getName());

        verify(hobbyRepository, times(1)).findAll();
    }

    @Test
    public void testGetHobbyById() {
        Hobby hobby = new Hobby();
        hobby.setId(1L);
        hobby.setName("Reading");

        when(hobbyRepository.findById(1L)).thenReturn(Optional.of(hobby));

        HobbyDTO hobbyDTO = hobbyService.getHobbyById(1L);
        assertEquals(1L, hobbyDTO.getId());
        assertEquals("Reading", hobbyDTO.getName());

        verify(hobbyRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetHobbyByIdNotFound() {
        when(hobbyRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            hobbyService.getHobbyById(1L);
        });

        assertEquals("Hobby not found", exception.getMessage());
        verify(hobbyRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateHobby() {
        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setId(1L);
        hobbyDTO.setName("Reading");

        Hobby hobby = new Hobby();
        hobby.setId(1L);
        hobby.setName("Reading");

        when(hobbyRepository.save(any(Hobby.class))).thenReturn(hobby);

        HobbyDTO createdHobbyDTO = hobbyService.createHobby(hobbyDTO);
        assertEquals(1L, createdHobbyDTO.getId());
        assertEquals("Reading", createdHobbyDTO.getName());

        verify(hobbyRepository, times(1)).save(any(Hobby.class));
    }
}
