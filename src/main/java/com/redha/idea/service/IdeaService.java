package com.redha.idea.service;

import com.redha.idea.model.Idea;

import java.util.List;
import java.util.Optional;

public interface IdeaService {
    Optional<Idea> findOneWithAuthorsById(Long id);
    List<Idea> findAll();
    Idea save(Idea idea);
}
