package com.rafael.app.blogru.modules.posts.mapper;

import com.rafael.app.blogru.modules.posts.document.Post;
import com.rafael.app.blogru.modules.posts.dto.PostDto;

public class PostMapper {

    public static PostDto mapPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .summary(post.getSummary())
                .topic_id(post.getTopic().getId())
                .subtopic_id(post.getSubtopic().getId())
                .user_id(post.getUser().getId())
                .register_date(post.getRegisterDate().toString())
//                .headers(post.getHeaders())
//                .paragraphs(post.getParagraphs())
                .build();
    }
}
