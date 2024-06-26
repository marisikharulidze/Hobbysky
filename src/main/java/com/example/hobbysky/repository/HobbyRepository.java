package com.example.hobbysky.repository;

import com.example.hobbysky.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby,Long> {

}
