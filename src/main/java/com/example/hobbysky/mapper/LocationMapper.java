package com.example.hobbysky.mapper;
import com.example.hobbysky.dto.LocationDTO;
import com.example.hobbysky.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationDTO locationToLocationDTO(Location location);

    Location locationDTOToLocation(LocationDTO locationDTO);
}