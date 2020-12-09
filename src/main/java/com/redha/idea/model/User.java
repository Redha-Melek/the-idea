package com.redha.idea.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@EqualsAndHashCode(of= {"name"})
@ToString(of= {"name"})
@Entity
@Table(name = "appuser")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    @NotEmpty(message = "you have to specify the name of the user")
    private String name;

}
//@Getter
//@Setter
//@EqualsAndHashCode(of = "id")
//@ToString(includeFieldNames = false)
