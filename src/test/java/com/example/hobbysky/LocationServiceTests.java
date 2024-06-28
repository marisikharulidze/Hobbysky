package com.example.hobbysky;

import com.example.hobbysky.dto.LocationDTO;
import com.example.hobbysky.mapper.LocationMapper;
import com.example.hobbysky.model.Location;
import com.example.hobbysky.repository.LocationRepository;
import com.example.hobbysky.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LocationServiceTests {

    private LocationRepository locationRepository;
    private LocationMapper locationMapper;
    private LocationService locationService;

    @BeforeEach
    public void setUp() {
        locationRepository = mock(LocationRepository.class);
        locationMapper = mock(LocationMapper.class);
        locationService = new LocationService(locationRepository, locationMapper);
    }

    @Test
    public void testFindLocationByIdNotFound() {
        when(locationRepository.findById(1L)).thenReturn(Optional.empty());

        LocationDTO locationDTO = locationService.findLocationById(1L);
        assertNull(locationDTO);

        verify(locationRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveLocation() {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(1L);
        locationDTO.setCountry("USA");
        locationDTO.setCity("New York");

        Location location = new Location();
        location.setId(1L);
        location.setCountry("USA");
        location.setCity("New York");

        when(locationMapper.locationDTOToLocation(locationDTO)).thenReturn(location);
        when(locationRepository.save(location)).thenReturn(location);

        locationService.saveLocation(locationDTO);

        verify(locationMapper, times(1)).locationDTOToLocation(locationDTO);
        verify(locationRepository, times(1)).save(location);
    }
}
