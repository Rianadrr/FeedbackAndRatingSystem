package com.example.feedback.repository;

import com.example.feedback.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepo {

    private final Map<Long, User> data = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public UserRepo() {
        // Seed data
        save(new User(null, "1@gmail.com", "Rian"));
        save(new User(null, "2@gmail.com", "Steven"));
    }

    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public User save(User m) {
        if (m.getId() == null) m.setId(seq.incrementAndGet());
        data.put(m.getId(), m);
        return m;
    }

    public void delete(Long id) {
        data.remove(id);
    }

    public boolean exists(Long id) {
        return data.containsKey(id);
    }
}
