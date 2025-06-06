package com.CRUD.CRUDOperations.service;

import com.CRUD.CRUDOperations.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();
    private Long nextId = 1L;
    public User createUser(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public Optional<User> getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = getUserById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}

