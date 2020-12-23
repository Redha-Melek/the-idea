package com.redha.idea.model.dto;

import lombok.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of= {"description", "authors"})
@ToString(of= {"description", "authors"})
public class IdeaDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "you have to specify the description dto")
    private String description;

    @Valid
    @NotEmpty(message = "You have to specify the userdto")
    private Set<UserDTO> authors;
}