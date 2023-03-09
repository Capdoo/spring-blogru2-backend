package com.rafael.app.blogru.modules.posts.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class ParagraphModel {

    @Id
    private String id;
    private String content;
    private Integer targetHeader;

}
