package com.rafael.app.blogru.modules.topics.service;

import com.rafael.app.blogru.modules.topics.document.Topic;
import com.rafael.app.blogru.modules.topics.dto.TopicDTO;

import java.util.List;

public interface TopicService {

    List<Topic> readAllTopics();

    //crud
    Topic createTopic(TopicDTO topicDTO);
    Topic readTopic(String id);
    Topic updateTopic(TopicDTO topicDTO);
    void deleteTopic(String id);

    //business...
    Topic readTopicByName(String name);
}
