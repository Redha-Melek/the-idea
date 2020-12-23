package com.redha.idea.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@EqualsAndHashCode(of= {"name"})
@ToString(of= {"name"})
@Entity
@Table(name = "appuser")
public class User extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    @NotEmpty(message = "you have to specify the name of the user")
    private String name;

    @Version
    private Long version;

}
