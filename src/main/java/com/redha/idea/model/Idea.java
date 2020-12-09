package com.redha.idea.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@EqualsAndHashCode(of= {"id", "description", "authors"})
@ToString(of= {"id", "description", "authors"})
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
    @ManyToMany(fetch = FetchType.LAZY, cascade = ALL)
    @JoinTable(name = "idea_appuser", //
            joinColumns = @JoinColumn(name = "idea_id", nullable = false, updatable = false), //
            inverseJoinColumns = @JoinColumn(name = "appuser_id", nullable = false, updatable = false))
    private Set<User> authors = new HashSet<>();


    public Idea addUser(User user) {
        this.authors.add(user);
        return this;
    }

    public Idea removeUser(User user) {
        this.authors.remove(user);
        return this;
    }

}
