package com.example.hobbysky.service;

import com.example.hobbysky.dto.UserDTO;
import com.example.hobbysky.mapper.UserMapper;
import com.example.hobbysky.model.Event;
import com.example.hobbysky.model.Role;
import com.example.hobbysky.model.User;
import com.example.hobbysky.repository.EventRepository;
import com.example.hobbysky.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, EventRepository eventRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.userMapper = userMapper;
    }

    //fixxxx
    public void saveUser(UserDTO userDTO) {
        validateUniqueEmail(userDTO.getEmail(), userDTO.getId());
        User user = userMapper.userDTOToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.customer);
        userRepository.save(user);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToUserDTO(user);
    }

    public List<UserDTO> searchPeople(String search) {
        List<User> users = userRepository.findByFirstNameContainingOrLastNameContaining(search, search);
        return users.stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
    }

    public UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null; // Or handle as per your application's requirements
        }

        String username = authentication.getName(); // Username of the currently logged in user
        User user = userRepository.findByEmail(username); // Find user by email
        return userMapper.userToUserDTO(user);
    }

    // Method to save or update user details
    public void updateUser(UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw new RuntimeException("User id is null");
        }
        validateUniqueEmail(userDTO.getEmail(), userDTO.getId());
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUserFromDTO(userDTO, user);
        userRepository.save(user);
    }

    // New method to validate the uniqueness of the email
    public void validateUniqueEmail(String email, Long userId) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null && !existingUser.getId().equals(userId)) {
            throw new RuntimeException("Email already in use");
        }
    }

    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return userMapper.userToUserDTO(user);
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with Email: " + email);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("customer")//USER
                .build();
    }
    public void joinEvent(Long userId, Long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        user.getEvents().add(event);
        userRepository.save(user);
    }

    public void unjoinEvent(Long userId, Long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        user.getEvents().remove(event);
        userRepository.save(user);
    }
}
