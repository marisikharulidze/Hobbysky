package com.example.hobbysky.service;

import com.example.hobbysky.dto.LocationDTO;
import com.example.hobbysky.mapper.LocationMapper;
import com.example.hobbysky.model.Location;
import com.example.hobbysky.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    public List<LocationDTO> findAll() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(locationMapper::locationToLocationDTO)
                .collect(Collectors.toList());
    }

    public LocationDTO findLocationById(Long id) {
        Location location = locationRepository.findById(id).orElse(null);
        return location != null ? locationMapper.locationToLocationDTO(location) : null;
    }

    public void saveLocation(LocationDTO locationDTO) {
        Location location = locationMapper.locationDTOToLocation(locationDTO);
        locationRepository.save(location);
    }
}