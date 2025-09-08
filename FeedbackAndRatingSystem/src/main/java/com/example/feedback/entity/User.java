package com.example.feedback.entity;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String fullName;

    // Constructor default
    public User() {}

    // Constructor full
    public User(Long id, String email, String fullName){
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }

    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setFullName(String fullName) { this.fullName = fullName; }
}