package com.redha.idea.service.impl;

import com.redha.idea.exception.BadRequestAlertException;
import com.redha.idea.mapper.IdeaMapper;
import com.redha.idea.model.Idea;
import com.redha.idea.model.User;
import com.redha.idea.model.dto.AuthorDTO;
import com.redha.idea.model.dto.IdeaDTO;
import com.redha.idea.repository.IdeaRepository;
import com.redha.idea.repository.UserRepository;
import com.redha.idea.service.IdeaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class IdeaServiceImpl implements IdeaService {

    private final IdeaRepository ideaRepository;
    private final UserRepository userRepository;
    private final IdeaMapper ideaMapper;

    @Override
    public Optional<IdeaDTO> findOneWithAuthorsById(Long id) {
        return ideaRepository.findById(id).map(ideaMapper::toDto);
    }

    @Override
    public Page<IdeaDTO> findAll(Pageable pageable) {
        return ideaRepository.findAll(pageable)
                .map(ideaMapper::toDto);
    }

    @Override
    public Page<IdeaDTO> findByUser(String name, Pageable pageable) {
        Optional<User> user = userRepository.findOneByName(name);
        if(user.isEmpty()) {
            throw new BadRequestAlertException("The user with the name " + name + " does not exist", "idea", "ideaUserNotexist");
        }
        return ideaRepository.findByAuthorsContaining(user.get(), pageable)
                .map(ideaMapper::toDto);
    }

    @Override
    public IdeaDTO save(IdeaDTO ideaDTO) {
        Idea idea = ideaMapper.toEntity(ideaDTO);
        idea.setAuthors(new HashSet<>());
        for(AuthorDTO authorDTO : ideaDTO.getAuthors()) {
            Optional<User> user = userRepository.findOneByName(authorDTO.getName());
            user.ifPresent(value -> idea.getAuthors().add(value));
        }
        if(idea.getAuthors().isEmpty()) {
            return null;
        }
        Idea ideaInserted = ideaRepository.save(idea);
        return ideaMapper.toDto(ideaInserted);
    }

    @Override
    public void delete(Long id) {
        ideaRepository.deleteById(id);
    }

}
