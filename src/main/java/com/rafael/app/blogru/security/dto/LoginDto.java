package com.rafael.app.blogru.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
