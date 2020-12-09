package com.redha.idea.model.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of= {"name"})
@ToString(of= {"name"})
public class AuthorDTO implements Serializable{

    private Long id;
    @NotEmpty(message = "you have to specify the name of the authordto")
    private String name;

}
//@Getter
//@Setter
//@EqualsAndHashCode(of = "id")
//@ToString(includeFieldNames = false)