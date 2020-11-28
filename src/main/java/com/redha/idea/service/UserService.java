package com.redha.idea.service;

import com.redha.idea.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    List<User> findAll();
    User save(User user);
}
