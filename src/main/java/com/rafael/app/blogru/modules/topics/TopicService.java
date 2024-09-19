package com.rafael.app.blogru.modules.topics;

import com.rafael.app.blogru.modules.topics.Topic;
import com.rafael.app.blogru.modules.topics.TopicDTO;

import java.util.List;

public interface TopicService {

    List<Topic> readAllTopics();

    //crud
    Topic createTopic(TopicDTO topicDTO);
    Topic readTopic(String id);
    Topic updateTopic(TopicDTO topicDTO);
    Topic deleteTopic(String id);

    //business...
    Topic readTopicByName(String name);
}
