package com.redha.idea.controller;

import com.redha.idea.model.Idea;
import com.redha.idea.model.dto.IdeaDTO;
import com.redha.idea.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/idea")
public class IdeaRessource {

    @Autowired
    IdeaRepository ideaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<IdeaDTO>> getIdea(@PathVariable Long id) {

        return new ResponseEntity<>(ideaRepository.findOneWithAuthorsById(id).map(IdeaDTO::new)
                , HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<IdeaDTO>> getIdeas() {
        List<Idea> ideaEntities = ideaRepository.findAll();
        List<IdeaDTO> ideaDTOS = ideaEntities.stream()
                .map(IdeaDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(ideaDTOS, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Idea> createIdea(@Valid @RequestBody Idea idea) {

        return new ResponseEntity<>(ideaRepository.save(idea), HttpStatus.CREATED);
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