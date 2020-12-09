package com.redha.idea.service;

import com.redha.idea.model.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findById(Long id);
    Page<UserDTO> findAll(Pageable pageable);
    UserDTO save(UserDTO user);

    void delete(Long id);
}
