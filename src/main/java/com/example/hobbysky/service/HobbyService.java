package com.example.hobbysky.service;

import com.example.hobbysky.dto.HobbyDTO;
import com.example.hobbysky.mapper.HobbyMapper;
import com.example.hobbysky.model.Hobby;
import com.example.hobbysky.repository.HobbyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HobbyService {

    private final HobbyRepository hobbyRepository;
    private final HobbyMapper hobbyMapper = HobbyMapper.INSTANCE;

    public HobbyService(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }

    public List<HobbyDTO> findAll() {
        List<Hobby> hobbies = hobbyRepository.findAll();
        return hobbies.stream().map(hobbyMapper::hobbyToHobbyDTO).collect(Collectors.toList());
    }

    public HobbyDTO getHobbyById(Long id) {
        Hobby hobby = hobbyRepository.findById(id).orElseThrow(() -> new RuntimeException("Hobby not found"));
        return hobbyMapper.hobbyToHobbyDTO(hobby);
    }

    public HobbyDTO createHobby(HobbyDTO hobbyDTO) {
        Hobby hobby = hobbyMapper.hobbyDTOToHobby(hobbyDTO);
        hobby = hobbyRepository.save(hobby);
        return hobbyMapper.hobbyToHobbyDTO(hobby);
    }
}