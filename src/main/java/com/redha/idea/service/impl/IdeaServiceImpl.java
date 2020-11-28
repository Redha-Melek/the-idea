package com.redha.idea.service.impl;

import com.redha.idea.model.Idea;
import com.redha.idea.repository.IdeaRepository;
import com.redha.idea.service.IdeaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IdeaServiceImpl implements IdeaService {

    private final IdeaRepository ideaRepository;

    @Override
    public Optional<Idea> findOneWithAuthorsById(Long id) {
        return ideaRepository.findOneWithAuthorsById(id);
    }

    @Override
    public List<Idea> findAll() {
        return ideaRepository.findAll();
    }

    @Override
    public Idea save(Idea idea) {
        return ideaRepository.save(idea);
    }

}
