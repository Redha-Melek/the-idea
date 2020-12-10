package com.redha.idea.model.dto;

import lombok.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of= {"name"})
@ToString(of= {"name"})
public class UserDTO implements Serializable {

    private Long id;
    @NotEmpty(message = "you have to specify the name of the userdto")
    private String name;
}
