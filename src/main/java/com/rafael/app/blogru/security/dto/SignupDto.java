package com.rafael.app.blogru.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SignupDto {

    @NotBlank
    @Size(max=30)
    private String firstName;

    @NotBlank
    @Size(max=30)
    private String lastName;

    @NotBlank
    @Size(min=3, max=30)
    private String username;

    @NotBlank
    @Size(max=60)
    @Email
    private String email;

    @NotBlank
    @Size(min=8, max=30)
    private String password;

    private Set<String> roles = new HashSet<>();

}
