package com.rafael.app.blogru.modules.headers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeaderDto {
    private String id;
    private String title;
    private String description;
    private String content;
}
