package com.redha.idea.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "idea")
public class Idea implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "you have to specify the description")
    private String description;

    @Valid
    @NotEmpty(message = "You have to specify the author")
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ideas")
    private Set<User> authors;

}
