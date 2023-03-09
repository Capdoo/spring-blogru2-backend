package com.rafael.app.blogru.modules.posts.models;

import org.springframework.data.annotation.Id;

public class HeaderModel {

    @Id
    private String id;
    private String content;

}
