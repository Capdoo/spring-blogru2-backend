package com.rafael.app.blogru.modules.topics;

public class TopicMapper {

    public static TopicDto mapTopicDto(Topic topic){

        return TopicDto.builder()
                .id(topic.getId())
                .description(topic.getName())
                .image(topic.getImage())
                .build();
    }

}
