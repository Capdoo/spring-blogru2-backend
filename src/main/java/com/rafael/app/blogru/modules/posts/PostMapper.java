package com.rafael.app.blogru.modules.posts;

import com.rafael.app.blogru.modules.sections.SectionDto;
import com.rafael.app.blogru.modules.sections.SectionMapper;
import com.rafael.app.blogru.modules.subtopics.SubtopicDto;
import com.rafael.app.blogru.modules.subtopics.SubtopicMapper;
import com.rafael.app.blogru.modules.topics.TopicDto;
import com.rafael.app.blogru.modules.topics.TopicMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto mapPostDto(Post post){
        TopicDto topicDto;
        SubtopicDto subtopicDto;
        List<SectionDto> listSectionsDto;

        listSectionsDto = post.getListSections().stream()
                .map(SectionMapper::mapSectionDto)
                .collect(Collectors.toList());

        topicDto = TopicMapper.mapTopicDto(post.getTopic());
        subtopicDto = SubtopicMapper.mapSubtopicDto(post.getSubtopic());

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .summary(post.getSummary())
                .topicId(post.getTopic().getId())
                .subtopicId(post.getSubtopic().getId())
                .topicDto(topicDto)
                .subtopicDto(subtopicDto)
                .userId(post.getUser().getId())
                .register_date(post.getRegisterDate().toString())
                .listSectionsDto(listSectionsDto)
                .build();
    }
}
