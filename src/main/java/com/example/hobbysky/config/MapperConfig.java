package com.example.hobbysky.config;

import com.example.hobbysky.mapper.EventMapper;
import com.example.hobbysky.mapper.LocationMapper;
import com.example.hobbysky.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UserMapper userMapper() {
        return UserMapper.INSTANCE;
    }

//    @Bean
//    public LocationMapper locationMapper() {
//        return LocationMapper.INSTANCE;
//    }

    @Bean
    public EventMapper eventMapper() {
        return EventMapper.INSTANCE;
    }
}