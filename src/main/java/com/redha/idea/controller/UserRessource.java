package com.redha.idea.controller;

import com.redha.idea.exception.BadRequestAlertException;
import com.redha.idea.exception.NotFoundAlertException;
import com.redha.idea.model.dto.UserDTO;
import com.redha.idea.service.UserService;
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

import static com.redha.idea.constant.ApiConstants.SUBPATH_ID;
import static com.redha.idea.constant.ApiConstants.USER_ROOT;


@RestController
@RequestMapping(USER_ROOT)
@AllArgsConstructor
public class UserRessource {

    private final String ENTITY_NAME = "User";
    private final UserService userService;

    @GetMapping(SUBPATH_ID)
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> userDTO = userService.findById(id);
        if (userDTO.isEmpty()) {
            throw new NotFoundAlertException("User not founded", ENTITY_NAME);
        }
        return ResponseEntity.ok().body(userDTO.get());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(Pageable pageable) {
        Page<UserDTO> page = userService.findAll(pageable);
        if (page.getTotalElements() <= 0) {
            throw new NotFoundAlertException("List of user is empty", ENTITY_NAME);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", ENTITY_NAME, "idexists");
        }
        userService.findByName(userDTO.getName()).ifPresent(value -> {throw new BadRequestAlertException("An existing user has the same name", ENTITY_NAME, "nameexists");});

        UserDTO userCreated = userService.save(userDTO);
        return ResponseEntity.created(new URI("/user/" + userCreated.getId())).body(userCreated);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw new BadRequestAlertException("An existing user cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<UserDTO> user = userService.findById(userDTO.getId());
        if (user.isEmpty()) {
            throw new NotFoundAlertException("The user not founded", ENTITY_NAME);
        }
        Optional<UserDTO> userExistsName = userService.findByName(userDTO.getName());
        if (userExistsName.isPresent() &&
                userExistsName.get().getId() != user.get().getId()) {
            throw new BadRequestAlertException("An existing user have the same name", ENTITY_NAME, "nameexists");
        }
        return ResponseEntity.ok().body(userService.update(userDTO));
    }

    @DeleteMapping(SUBPATH_ID)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<UserDTO> user = userService.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundAlertException("The user not founded", ENTITY_NAME);
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}