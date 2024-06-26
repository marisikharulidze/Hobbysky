package com.example.hobbysky.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false, length = 40)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 40)
    private String lastName;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "phoneNum", length = 20)
    private String phoneNum;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",length = 10)
    private Role role;

    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location locationId;

    @ManyToMany
    private Set<Hobby> hobbies;

    @ManyToMany(mappedBy = "users")
    private Set<Event> events = new HashSet<>();

    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }


//    @ManyToMany
//    @JoinTable(
//            name = "hobbies",
//            joinColumns = @JoinColumn(name = "userId"),
//            inverseJoinColumns = @JoinColumn(name = "hobbyId")
//    )
//    private Set<Hobby> hobbies;
}


//
//    @ManyToMany
//    @JoinTable(
//            name = "user_hobbies",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "hobby_id")
//    )
//    private Set<Hobby> hobbies;
//
//    @ManyToMany
//    @JoinTable(
//            name = "user_events",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "event_id")
//    )
//    private Set<Event> events;

// Getters and setters...
//
//    public Set<Hobby> getHobbies() {
//        return hobbies;
//    }
//
//    public void setHobbies(Set<Hobby> hobbies) {
//        this.hobbies = hobbies;
//    }
//
//    public Set<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(Set<Event> events) {
//        this.events = events;
//    }
