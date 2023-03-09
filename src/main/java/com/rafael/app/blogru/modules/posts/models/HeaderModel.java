package com.rafael.app.blogru.modules.posts.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class HeaderModel {

    @Id
    private String id;
    private String content;

}
