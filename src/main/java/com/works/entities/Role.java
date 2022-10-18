package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @NotBlank(message = "Role name cannot be blank")
    @Length(min = 2 , max = 75 ,message = "Category name must be minimum 2 and maximum 75 character")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
