package com.redha.idea.model.dto;


import com.redha.idea.model.Idea;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class IdeaDTO implements Serializable {

    private String description;

    private Set<AuthorDTO> authors;
}
