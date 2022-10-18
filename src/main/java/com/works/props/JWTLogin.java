package com.works.props;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class JWTLogin {

    @Email
    @NotBlank(message = "Email can not be blank!")
    private String email;

    @Length(min = 5,max =16, message = "Password must be minimum 5 and max 16 character")
    @NotBlank(message = "Password can not be blank!")
    private String password;

}