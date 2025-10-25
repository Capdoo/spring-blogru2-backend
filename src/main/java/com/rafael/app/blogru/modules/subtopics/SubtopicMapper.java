package com.rafael.app.blogru.modules.subtopics;


public class SubtopicMapper {

    public static SubtopicDto mapSubtopicDto(Subtopic subtopic){

        return SubtopicDto.builder()
                .id(subtopic.getId())
                .description(subtopic.getName())
                .image(subtopic.getImage())
                .build();
    }
}