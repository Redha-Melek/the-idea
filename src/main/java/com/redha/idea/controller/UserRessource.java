package com.redha.idea.controller;

import com.redha.idea.model.Idea;
import com.redha.idea.model.User;
import com.redha.idea.model.dto.IdeaDTO;
import com.redha.idea.model.dto.UserDTO;
import com.redha.idea.repository.IdeaRepository;
import com.redha.idea.repository.UserRepository;
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
@RequestMapping("/user")
public class UserRessource {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}/ideas")
    public ResponseEntity<Optional<UserDTO>> getUserWithIdeas(@PathVariable Long id) {

        return new ResponseEntity<>(userRepository.findById(id).map(UserDTO::new)
                , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> getUser(@PathVariable Long id) {

        return new ResponseEntity<>(userRepository.findOneById(id).map(UserDTO::new)
                , HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getUsersWithIdeas() {
        List<User> userEntities = userRepository.findAll();
        List<UserDTO> userDTOS = userEntities.stream()
                .map(UserDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
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