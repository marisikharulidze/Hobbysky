package com.example.hobbysky.config;

import com.example.hobbysky.mapper.HobbyMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //change
    @Bean
    public HobbyMapper hobbyMapper() {
        return Mappers.getMapper(HobbyMapper.class);
    }
}