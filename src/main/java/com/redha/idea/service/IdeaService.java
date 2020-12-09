package com.redha.idea.service;

import com.redha.idea.model.dto.IdeaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IdeaService {
    Optional<IdeaDTO> findOneWithAuthorsById(Long id);
    Page<IdeaDTO> findAll(Pageable pageable);
    IdeaDTO save(IdeaDTO ideaDTO);

    void delete(Long id);

    Page<IdeaDTO> findByUser(String name, Pageable pageable);
}
