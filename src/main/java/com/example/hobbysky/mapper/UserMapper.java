package com.example.hobbysky.mapper;

import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNum", target = "phoneNum")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "locationId", target = "locationId")
    @Mapping(source = "hobbies", target = "hobbies")
    @Mapping(source = "events", target = "events")
    UserDTO userToUserDTO(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNum", target = "phoneNum")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "locationId", target = "locationId")
    @Mapping(source = "hobbies", target = "hobbies")
    @Mapping(source = "events", target = "events")
    User userDTOToUser(UserDTO userDTO);

    //NEW
    void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);
}
