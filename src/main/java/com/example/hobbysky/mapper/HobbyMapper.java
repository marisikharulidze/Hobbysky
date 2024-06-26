package com.example.hobbysky.mapper;

import com.example.hobbysky.dto.HobbyDTO;
import com.example.hobbysky.model.Hobby;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HobbyMapper {

    HobbyMapper INSTANCE = Mappers.getMapper(HobbyMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    HobbyDTO hobbyToHobbyDTO(Hobby hobby);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Hobby hobbyDTOToHobby(HobbyDTO hobbyDTO);
}