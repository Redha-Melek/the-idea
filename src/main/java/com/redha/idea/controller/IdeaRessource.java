package com.redha.idea.controller;

import com.redha.idea.exception.BadRequestAlertException;
import com.redha.idea.exception.NotFoundAlertException;
import com.redha.idea.model.dto.IdeaDTO;
import com.redha.idea.service.IdeaService;
import com.redha.idea.utilities.PaginationUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static com.redha.idea.constant.ApiConstants.*;


@RestController
@RequestMapping(IDEA_ROOT)
@AllArgsConstructor
public class IdeaRessource {

    private final String ENTITY_NAME = "Idea";
    private final IdeaService ideaService;

    @GetMapping(SUBPATH_ID)
    public ResponseEntity<IdeaDTO> getIdea(@PathVariable Long id) {
        Optional<IdeaDTO> idea = ideaService.findOneWithAuthorsById(id);
        if (idea.isEmpty()) {
            throw new NotFoundAlertException("The idea not founded", ENTITY_NAME);
        }
        return ResponseEntity.ok().body(idea.get());
    }

    @GetMapping
    public ResponseEntity<List<IdeaDTO>> getIdeas(Pageable pageable) {
        Page<IdeaDTO> page = ideaService.findAll(pageable);
        if (page.getTotalElements() <= 0) {
            throw new NotFoundAlertException("List of idea is empty", ENTITY_NAME);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping(SUBPATH_IDEA_USER_NAME)
    public ResponseEntity<List<IdeaDTO>> getIdeasByUser(@PathVariable String name, Pageable pageable) {
        Page<IdeaDTO> page = ideaService.findByUser(name, pageable);
        if ( page.getTotalElements() <= 0) {
            throw new NotFoundAlertException("List of idea is empty", ENTITY_NAME);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping
    public ResponseEntity<IdeaDTO> createIdea(@Valid @RequestBody IdeaDTO ideaDTO) throws URISyntaxException {
        if (ideaDTO.getId() != null) {
            throw new BadRequestAlertException("A new idea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IdeaDTO ideaCreated = ideaService.save(ideaDTO);
        return ResponseEntity.created(new URI("/idea/" + ideaCreated.getId())).body(ideaCreated);
    }

    @PutMapping
    public ResponseEntity<IdeaDTO> updateIdea(@Valid @RequestBody IdeaDTO ideaDTO) {
        if (ideaDTO.getId() == null) {
            throw new BadRequestAlertException("An existing idea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<IdeaDTO> idea = ideaService.findOneWithAuthorsById(ideaDTO.getId());
        if (idea.isEmpty()) {
            throw new NotFoundAlertException("The idea not founded", ENTITY_NAME);
        }
        return ResponseEntity.ok().body(ideaService.save(ideaDTO));
    }

    @DeleteMapping(SUBPATH_ID)
    public ResponseEntity<Void> deleteIdea(@PathVariable Long id) {
        Optional<IdeaDTO> idea = ideaService.findOneWithAuthorsById(id);
        if (idea.isEmpty()) {
            throw new NotFoundAlertException("The idea not founded", ENTITY_NAME);
        }
        ideaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}