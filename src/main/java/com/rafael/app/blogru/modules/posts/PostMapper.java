package com.rafael.app.blogru.modules.posts;

import com.rafael.app.blogru.modules.sections.SectionDto;
import com.rafael.app.blogru.modules.sections.SectionMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto mapPostDto(Post post){
        List<SectionDto> listSectionsDto;

        listSectionsDto = post.getListSections().stream()
                .map(SectionMapper::mapSectionDto)
                .collect(Collectors.toList());

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .summary(post.getSummary())
                .topic_id(post.getTopic().getId())
                .subtopic_id(post.getSubtopic().getId())
                .user_id(post.getUser().getId())
                .register_date(post.getRegisterDate().toString())
                .listSectionsDto(listSectionsDto)
//                .headers(post.getHeaders())
//                .paragraphs(post.getParagraphs())
                .build();
    }
}
