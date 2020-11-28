package com.redha.idea.controller;

import com.redha.idea.mapper.UserMapper;
import com.redha.idea.model.User;
import com.redha.idea.model.dto.UserDTO;
import com.redha.idea.service.UserService;
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
@RequestMapping("/user")
public class UserRessource {

    UserService userService;
    UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserWithIdeas(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.toDto(user.get())
                , HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getUsersWithIdeas() {
        List<User> userEntities = userService.findAll();
        if (userEntities.isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        List<UserDTO> userDTOS = userMapper.toDto(userEntities);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
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