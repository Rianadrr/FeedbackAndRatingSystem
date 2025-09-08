package com.example.feedback.controller;

import com.example.feedback.entity.Feedback;
import com.example.feedback.entity.User;
import com.example.feedback.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody User user) {
        User savedUser = service.createUser(user);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User berhasil dibuat");
        response.put("data", savedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // POST /feedbacks
    @PostMapping("/feedbacks")
    public ResponseEntity<Map<String, Object>> createFeedback(
            @RequestParam Long userId,
            @Valid @RequestBody Feedback feedback) {

        Feedback savedFeedback = service.createFeedback(userId, feedback);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Feedback berhasil dibuat");
        response.put("data", savedFeedback);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // GET /feedbacks
    @GetMapping("/feedbacks")
    public List<Feedback> getAllFeedbacks(@RequestParam(required = false) Integer rating) {
        return service.getAllFeedbacks(rating);
    }

    // GET /feedbacks/{id}
    @GetMapping("/feedbacks/{id}")
    public Feedback getFeedback(@PathVariable Long id) {
        return service.getFeedback(id);
    }

    // PUT /feedbacks/{id}
    @PutMapping("/feedbacks/{id}")
    public ResponseEntity<Map<String, Object>> updateFeedback(
            @PathVariable Long id,
            @Valid @RequestBody Feedback feedback) {

        Feedback updatedFeedback = service.updateFeedback(id, feedback);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Feedback berhasil diperbarui");
        response.put("data", updatedFeedback);

        return ResponseEntity.ok(response);
    }


    // DELETE /feedbacks/{id}
    @DeleteMapping("/feedbacks/{id}")
    public ResponseEntity<Map<String, String>> deleteFeedback(@PathVariable Long id) {
        service.deleteFeedback(id);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Feedback dengan ID " + id + " berhasil dihapus");

        return ResponseEntity.ok(response);
    }

    // GET /ratings-summary
    @GetMapping("/ratings-summary")
    public double getAverageRating() {
        return service.getAverageRating();
    }

    // GET /users/{id}/feedbacks
    @GetMapping("/users/{id}/feedbacks")
    public List<Feedback> getFeedbacksByUser(@PathVariable Long id) {
        return service.getFeedbacksByUser(id);
    }
}
