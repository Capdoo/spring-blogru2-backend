package com.rafael.app.blogru.modules.headers;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Header {
    @Id
    private String id;
    private String title;
}
