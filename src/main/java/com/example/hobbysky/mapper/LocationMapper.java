package com.example.hobbysky.mapper;

import com.example.hobbysky.dto.LocationDTO;
import com.example.hobbysky.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component // Ensure it is recognized as a Spring component
public interface LocationMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "city", target = "city")
    LocationDTO locationToLocationDTO(Location location);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "city", target = "city")
    Location locationDTOToLocation(LocationDTO locationDTO);

    // Method to update Location entity from LocationDTO
    @Mapping(source = "country", target = "country")
    @Mapping(source = "city", target = "city")
    void updateLocationFromDTO(LocationDTO locationDTO, @MappingTarget Location location);
}
