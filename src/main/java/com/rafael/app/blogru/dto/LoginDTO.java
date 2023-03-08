package com.rafael.app.blogru.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
