package com.redha.idea.model.dto;


import com.redha.idea.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class AuthorDTO implements Serializable{

    private String name;

    public AuthorDTO (User user) {
        this.name = user.getName();
    }
}
//@Getter
//@Setter
//@EqualsAndHashCode(of = "id")
//@ToString(includeFieldNames = false)
