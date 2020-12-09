package com.redha.idea.controller;

import com.redha.idea.exception.BadRequestAlertException;
import com.redha.idea.model.dto.IdeaDTO;
import com.redha.idea.service.IdeaService;
import com.redha.idea.utilities.PaginationUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(idea.get()
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<IdeaDTO>> getIdeas(Pageable pageable) {
        Page<IdeaDTO> page = ideaService.findAll(pageable);
        if (page.getTotalElements() <= 0) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping(SUBPATH_IDEA_USER_NAME)
    public ResponseEntity<List<IdeaDTO>> getIdeasByUser(@PathVariable String name, Pageable pageable) {
        Page<IdeaDTO> page = ideaService.findByUser(name, pageable);
        if (page == null || page.getTotalElements() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping
    public ResponseEntity<IdeaDTO> createIdea(@Valid @RequestBody IdeaDTO ideaDTO) {
        if (ideaDTO.getId() != null) {
            throw new BadRequestAlertException("A new idea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return new ResponseEntity<>(ideaService.save(ideaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<IdeaDTO> updateIdea(@Valid @RequestBody IdeaDTO ideaDTO) {
        if (ideaDTO.getId() == null) {
            throw new BadRequestAlertException("An existing idea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<IdeaDTO> idea = ideaService.findOneWithAuthorsById(ideaDTO.getId());
        if (idea.isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ideaService.save(ideaDTO), HttpStatus.OK);
    }

    @DeleteMapping(SUBPATH_ID)
    public ResponseEntity<Void> deleteIdea(@PathVariable Long id) {
        Optional<IdeaDTO> idea = ideaService.findOneWithAuthorsById(id);
        if (idea.isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        ideaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}