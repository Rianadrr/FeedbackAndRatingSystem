package com.example.feedback.service;

import com.example.feedback.entity.Feedback;
import com.example.feedback.entity.User;
import com.example.feedback.repository.FeedbackRepo;
import com.example.feedback.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo; // ganti FeedbackRepository

    @Autowired
    private UserRepo userRepo; // ganti UserRepository

    public Feedback createFeedback(Long userId, Feedback feedback) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        feedback.setUser(user);
        return feedbackRepo.save(feedback);
    }

    public List<Feedback> getAllFeedbacks(Integer rating) {
        List<Feedback> all = feedbackRepo.findAll();
        if (rating != null) {
            List<Feedback> filtered = new ArrayList<>();
            for (Feedback f : all) {
                if (f.getRating() == rating) filtered.add(f);
            }
            return filtered;
        }
        return all;
    }

    public Feedback getFeedback(Long id) {
        return feedbackRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    public Feedback updateFeedback(Long id, Feedback feedbackDetails) {
        Feedback feedback = getFeedback(id);
        feedback.setMessage(feedbackDetails.getMessage());
        feedback.setRating(feedbackDetails.getRating());
        return feedbackRepo.save(feedback);
    }

    public void deleteFeedback(Long id) {
        feedbackRepo.delete(id);
    }

    public double getAverageRating() {
        List<Feedback> feedbacks = feedbackRepo.findAll();
        return feedbacks.stream().mapToInt(Feedback::getRating).average().orElse(0);
    }

    public List<Feedback> getFeedbacksByUser(Long userId) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback f : feedbackRepo.findAll()) {
            if (f.getUser().getId().equals(userId)) result.add(f);
        }
        return result;
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }
}
