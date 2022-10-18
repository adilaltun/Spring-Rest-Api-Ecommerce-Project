package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class User extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "Username cannot be blank")
    @Length(min = 2 , max = 75 ,message = "Username must be minimum 2 and maximum 75 character")
    private String userName;

    @NotBlank(message = "User surname cannot be blank")
    @Length(min = 2 , max = 75 ,message = "User surname must be minimum 2 and maximum 75 character")
    private String userSurname;

    @Email
    @NotBlank(message = "Email name cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private boolean enabled=true;
    private boolean tokenExpired=true;

    @ManyToMany
    @JoinTable( name = "user_role",
            joinColumns = @JoinColumn( name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn( name = "role_id", referencedColumnName = "roleId")
    )

    private List<Role> roles;


}

