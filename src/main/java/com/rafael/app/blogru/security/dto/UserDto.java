package com.rafael.app.blogru.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class UserDto {

    @NotBlank
    @Size(max=30)
    private String firstName;

    @NotBlank
    @Size(max=30)
    private String lastName;

    @NotBlank
    @Size(min=3, max=30)
    private String username;

}
