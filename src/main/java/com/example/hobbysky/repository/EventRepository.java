package com.example.hobbysky.repository;

import com.example.hobbysky.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByNameContainingIgnoreCase(String name);
}
