package com.redha.idea.service.impl;

import com.redha.idea.mapper.UserMapper;
import com.redha.idea.model.User;
import com.redha.idea.model.dto.UserDTO;
import com.redha.idea.repository.UserRepository;
import com.redha.idea.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return Optional.of(userRepository.save(user))
                .map(userMapper::toDto).get();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
