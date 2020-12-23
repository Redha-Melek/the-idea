package com.redha.idea.service;

import com.redha.idea.model.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findById(Long id);
    Optional<UserDTO> findByName(String name);
    Page<UserDTO> findAll(Pageable pageable);
    UserDTO save(UserDTO user);
    UserDTO update(UserDTO userDTO);

    void delete(Long id);
}
