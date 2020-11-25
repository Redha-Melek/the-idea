package com.redha.idea.model.dto;


import com.redha.idea.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDTO implements Serializable{

    private String name;
    private List<IdeaDTO> ideas;
    public UserDTO(User user) {
        this.name = user.getName();
        this.ideas = user.getIdeas().stream().map(IdeaDTO::new).collect(Collectors.toList());
    }
}
//@Getter
//@Setter
//@EqualsAndHashCode(of = "id")
//@ToString(includeFieldNames = false)
