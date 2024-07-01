package com.example.hobbysky.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@Data
//@RequiredArgsConstructor
public class EventDTO {
    private Long id;
    private String name;
    private Date date;
    private LocationDTO location;
    private String description;
    private HobbyDTO hobby;
    private Integer numOfParticipants;
    private String status;
//    private MultipartFile imageFile;
    private String image;
    private Date creationDate;
    private Date lastModifiedDate;
    //new remove?????
    private Set<UserDTO> users;
    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public HobbyDTO getHobby() {
        return hobby;
    }

    public void setHobby(HobbyDTO hobby) {
        this.hobby = hobby;
    }

    public Integer getNumOfParticipants() {
        return numOfParticipants;
    }

    public void setNumOfParticipants(Integer numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public MultipartFile getImageFile() {
//        return imageFile;
//    }
//
//    public void setImageFile(MultipartFile imageFile) {
//        this.imageFile = imageFile;
//    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
