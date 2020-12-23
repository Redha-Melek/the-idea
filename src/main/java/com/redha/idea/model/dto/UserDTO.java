package com.redha.idea.model.dto;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of= {"name"})
@ToString(of= {"name"})
public class UserDTO implements Serializable {

    private Long id;
    @NotEmpty(message = "you have to specify the name of the userdto")
    private String name;
    private Date createdDate;
    private Date lastModifiedDate;
    private Long version;
}
