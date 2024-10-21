package com.rafael.app.blogru.modules.topics;

import java.util.List;

public interface TopicService {

    List<Topic> readAllTopics();

    //crud
    Topic createTopic(TopicDto topicDTO);
    Topic readTopic(String id);
    Topic updateTopic(TopicDto topicDTO);
    Topic deleteTopic(String id);

    //business...
    Topic readTopicByName(String name);
}
