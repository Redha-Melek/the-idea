package com.redha.idea.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "author")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "you have to specify the name of the author")
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "idea_author", //
            joinColumns = @JoinColumn(name = "author_id", nullable = false, updatable = false), //
            inverseJoinColumns = @JoinColumn(name = "idea_id", nullable = false, updatable = false))
    private Set<Idea> ideas;
}
//@Getter
//@Setter
//@EqualsAndHashCode(of = "id")
//@ToString(includeFieldNames = false)
