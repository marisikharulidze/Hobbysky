package com.example.hobbysky.service;


import com.example.hobbysky.dto.ProfileDTO;
import com.example.hobbysky.mapper.ProfileMapper;
import com.example.hobbysky.model.Profile;
import com.example.hobbysky.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private static final ProfileMapper profileMapper = ProfileMapper.INSTANCE;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<ProfileDTO> findAll() {
        return profileRepository.findAll()
                .stream()
                .map(profileMapper::profileToProfileDTO)
                .collect(Collectors.toList());
    }

    public ProfileDTO findById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        return profileMapper.profileToProfileDTO(profile);
    }

    public ProfileDTO saveProfile(ProfileDTO profileDTO) {
        Profile profile = profileMapper.profileDTOToProfile(profileDTO);
        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.profileToProfileDTO(savedProfile);
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        existingProfile.setDescription(profileDTO.getDescription());
        existingProfile.setImage(profileDTO.getImage());
//        existingProfile.setUser(profileMapper.userDTOToUser(profileDTO.getUser()));
        existingProfile.setCreationDate(profileDTO.getCreationDate());
        existingProfile.setLastModifiedDate(profileDTO.getLastModifiedDate());

        Profile updatedProfile = profileRepository.save(existingProfile);
        return profileMapper.profileToProfileDTO(updatedProfile);
    }
}