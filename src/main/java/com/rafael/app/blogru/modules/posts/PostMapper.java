package com.rafael.app.blogru.modules.posts;

import com.rafael.app.blogru.modules.sections.SectionDto;
import com.rafael.app.blogru.modules.sections.SectionMapper;
import com.rafael.app.blogru.modules.subtopics.SubtopicDto;
import com.rafael.app.blogru.modules.subtopics.SubtopicMapper;
import com.rafael.app.blogru.modules.topics.TopicDto;
import com.rafael.app.blogru.modules.topics.TopicMapper;
import com.rafael.app.blogru.security.document.User;
import com.rafael.app.blogru.security.dto.UserDto;
import com.rafael.app.blogru.security.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto mapPostDto(Post post){
        TopicDto topicDto;
        SubtopicDto subtopicDto;
        List<SectionDto> listSectionsDto;
        UserDto userDto;

        listSectionsDto = post.getListSections().stream()
                .map(SectionMapper::mapSectionDto)
                .collect(Collectors.toList());

        topicDto = TopicMapper.mapTopicDto(post.getTopic());
        subtopicDto = SubtopicMapper.mapSubtopicDto(post.getSubtopic());
        userDto = UserMapper.mapUserDto(post.getUser());

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
                .userDto(userDto)
                .build();
    }
}
