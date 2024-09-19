package com.rafael.app.blogru.modules.paragraphs;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document
@Data
@Builder
public class Paragraph {
    @Id
    private String id;
    private String title;
    private String description;
    private String content;
}
