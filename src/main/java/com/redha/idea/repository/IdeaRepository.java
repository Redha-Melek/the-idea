package com.redha.idea.repository;

import com.redha.idea.model.Idea;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdeaRepository extends JpaRepository<Idea, Long> {
    @EntityGraph(attributePaths = "authors")
    Optional<Idea> findOneWithAuthorsById(Long id);


}