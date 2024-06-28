package com.example.hobbysky;

import com.example.hobbysky.dto.HobbyDTO;
import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.model.Role;
import com.example.hobbysky.model.User;
import com.example.hobbysky.repository.UserRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {

    private User user;
    private Validator validator;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        passwordEncoder = new BCryptPasswordEncoder();
        user = new User(
                1L,
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                "john.doe@example.com",
                "+1234567890",
                LocalDateTime.now(),
                LocalDateTime.now(),
                Role.USER,
                passwordEncoder.encode("password123"),
                null,
                new HashSet<>(),
                new HashSet<>()
        );
    }
    @Test
    public void testUserGettersAndSetters() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPhoneNum("+1234567890");
        userDTO.setCreationDate(LocalDateTime.now());
        userDTO.setLastModifiedDate(LocalDateTime.now());
        userDTO.setRole("USER");
        userDTO.setPassword("password123");

        assertEquals("John", userDTO.getFirstName());
        assertEquals("Doe", userDTO.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), userDTO.getDateOfBirth());
        assertEquals("john.doe@example.com", userDTO.getEmail());
        assertEquals("+1234567890", userDTO.getPhoneNum());
        assertNotNull(userDTO.getCreationDate());
        assertNotNull(userDTO.getLastModifiedDate());
        assertEquals("USER", userDTO.getRole());
        assertEquals("password123", userDTO.getPassword());
    }

    @Test
    public void testUserValidation() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("");  // Invalid: empty
        userDTO.setLastName("");   // Invalid: empty
        userDTO.setDateOfBirth(LocalDate.now().plusDays(1));  // Invalid: future date
        userDTO.setEmail("invalid-email");  // Invalid: not an email
        userDTO.setPhoneNum("12345");  // Invalid: too short
        userDTO.setRole("USER1234567890");  // Invalid: too long
        userDTO.setPassword("short");  // Invalid: too short

        var violations = validator.validate(userDTO);

        assertFalse(violations.isEmpty());
        assertEquals(7, violations.size());
    }

    @Test
    public void testUserHobbiesAndEvents() {
        UserDTO userDTO = new UserDTO();
        Set<HobbyDTO> hobbies = Collections.singleton(new HobbyDTO());
        Set<HobbyDTO> events = Collections.singleton(new HobbyDTO());

        userDTO.setHobbies(hobbies);
        userDTO.setEvents(events);

        assertEquals(hobbies, userDTO.getHobbies());
        assertEquals(events, userDTO.getEvents());
    }

    @Test
    public void testUserAgeCalculation() {
        User user = new User();
        user.setDateOfBirth(LocalDate.of(2000, 1, 1));

        int age = user.getAge();

        assertEquals(24, age);  // assuming the current year is 2024
    }

    @Test
    public void testUserConstructor() {
        User user = new User(
                1L,
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                "john.doe@example.com",
                "+1234567890",
                LocalDateTime.now(),
                LocalDateTime.now(),
                Role.USER,
                passwordEncoder.encode("password123"),
                null,
                Collections.emptySet(),
                Collections.emptySet()
        );

        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), user.getDateOfBirth());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("+1234567890", user.getPhoneNum());
        assertNotNull(user.getCreationDate());
        assertNotNull(user.getLastModifiedDate());
        assertEquals(Role.USER, user.getRole());
        assertTrue(passwordEncoder.matches("password123", user.getPassword()));
        assertNull(user.getLocationId());
        assertTrue(user.getHobbies().isEmpty());
        assertTrue(user.getEvents().isEmpty());
    }

    @Test
    public void testUserPasswordEncoding() {
        assertTrue(passwordEncoder.matches("password123", user.getPassword()));
    }
    @Test
    public void testUserDTOValidation() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("");
        userDTO.setLastName("Doe");
        userDTO.setDateOfBirth(LocalDate.of(2025, 1, 1));
        userDTO.setEmail("invalid-email");
        userDTO.setPhoneNum("12345");
        userDTO.setPassword("short");

        var violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());

        violations.forEach(violation -> {
            System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
        });
    }

    @Test
    public void testUserDTOValid() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPhoneNum("+1234567890");
        userDTO.setPassword("password123");

        var violations = validator.validate(userDTO);
        assertTrue(violations.isEmpty());
    }
}
