package com.redha.idea.controller;

import com.redha.idea.mapper.IdeaMapper;
import com.redha.idea.model.Idea;
import com.redha.idea.model.dto.IdeaDTO;
import com.redha.idea.service.IdeaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("/idea")
public class IdeaRessource {

    private final IdeaService ideaService;
    private final IdeaMapper ideaMapper;

    @GetMapping("/{id}")
    public ResponseEntity<IdeaDTO> getIdea(@PathVariable Long id) {
        Optional<Idea> idea = ideaService.findOneWithAuthorsById(id);
        if (idea.isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ideaMapper.toDto(idea.get())
                , HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<IdeaDTO>> getIdeas() {
        List<Idea> ideaEntities = ideaService.findAll();
        if (ideaEntities.isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        List<IdeaDTO> ideaDTOS = ideaMapper.toDto(ideaEntities);
        return new ResponseEntity<>(ideaDTOS, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Idea> createIdea(@Valid @RequestBody Idea idea) {

        return new ResponseEntity<>(ideaService.save(idea), HttpStatus.CREATED);
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