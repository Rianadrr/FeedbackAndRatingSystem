package com.example.feedback.repository;

import com.example.feedback.entity.Feedback;
import com.example.feedback.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class FeedbackRepo {

    private final Map<Long, Feedback> data = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    // Tambahkan reference ke UserRepo untuk seed data
    private final UserRepo userRepo;

    public FeedbackRepo(UserRepo userRepo) { // inject UserRepo
        this.userRepo = userRepo;

        // Ambil user dari UserRepo
        User user1 = userRepo.findById(1L).orElse(new User(1L, "1@gmail.com", "Rian"));
        User user2 = userRepo.findById(2L).orElse(new User(2L, "2@gmail.com", "Steven"));

        // Seed data Feedback
        save(new Feedback(null, user1, "Halo", 5));
        save(new Feedback(null, user2, "Hei", 5));
    }

    public List<Feedback> findAll() { return new ArrayList<>(data.values()); }

    public Optional<Feedback> findById(Long id) { return Optional.ofNullable(data.get(id)); }

    public Feedback save(Feedback m) {
        if (m.getId() == null) m.setId(seq.incrementAndGet());
        data.put(m.getId(), m);
        return m;
    }

    public void delete(Long id) { data.remove(id); }

    public boolean exists(Long id) { return data.containsKey(id); }
}
