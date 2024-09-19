package com.rafael.app.blogru.modules.paragraphs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParagraphDto {
    private String id;
    private String title;
    private String description;
    private String content;
}
