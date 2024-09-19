package com.rafael.app.blogru.modules.headers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeaderDto {
    private String id;
    private String title;
}
