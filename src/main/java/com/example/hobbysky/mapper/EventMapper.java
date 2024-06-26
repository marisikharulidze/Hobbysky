package com.example.hobbysky.mapper;

import com.example.hobbysky.dto.EventDTO;
import com.example.hobbysky.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = {LocationMapper.class, HobbyMapper.class})
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "hobby", target = "hobby")
    @Mapping(source = "numOfParticipants", target = "numOfParticipants")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    EventDTO eventToEventDTO(Event event);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "hobby", target = "hobby")
    @Mapping(source = "numOfParticipants", target = "numOfParticipants")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
    Event eventDTOToEvent(EventDTO eventDTO);
}