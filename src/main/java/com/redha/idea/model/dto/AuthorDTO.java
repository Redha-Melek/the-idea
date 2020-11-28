package com.redha.idea.model.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorDTO implements Serializable{

    private String name;

}
//@Getter
//@Setter
//@EqualsAndHashCode(of = "id")
//@ToString(includeFieldNames = false)
