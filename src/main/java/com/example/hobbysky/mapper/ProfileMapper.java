package com.example.hobbysky.mapper;

import com.example.hobbysky.dto.ProfileDTO;
import com.example.hobbysky.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    ProfileDTO profileToProfileDTO(Profile profile);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    Profile profileDTOToProfile(ProfileDTO profileDTO);
}