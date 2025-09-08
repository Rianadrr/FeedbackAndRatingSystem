package com.example.feedback.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    @Size(min = 10)
    private String message;

    @Min(1)
    @Max(5)
    private int rating;

    public Feedback (Long id, User user, String message, int rating){
        this.id = id; this.user = user; this.message = message; this.rating = rating;
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public int getRating() {
        return rating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
