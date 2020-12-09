package com.redha.idea.repository;

import com.redha.idea.model.Idea;
import com.redha.idea.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<Idea, Long> {

    Page<Idea> findByAuthorsContaining(User author, Pageable pageable);

}