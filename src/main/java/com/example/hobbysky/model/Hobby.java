package com.example.hobbysky.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hobby")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;


    public Hobby(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hobby() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Hobby(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
}