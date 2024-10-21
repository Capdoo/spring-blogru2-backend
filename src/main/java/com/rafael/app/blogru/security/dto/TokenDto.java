package com.rafael.app.blogru.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDto {

    private String userId;

    private String accessToken;

    private String refreshToken;

}


