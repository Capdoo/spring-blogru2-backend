package com.rafael.app.blogru.modules.topics.service;

import com.rafael.app.blogru.modules.topics.document.Topic;
import com.rafael.app.blogru.modules.topics.dto.TopicDTO;
import com.rafael.app.blogru.modules.topics.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public List<Topic> readAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic createTopic(TopicDTO topicDTO) {
        Topic createTopic = new Topic();
        createTopic.setName(topicDTO.getName());
        createTopic.setDescription(topicDTO.getDescription());
        createTopic.setRegisterDate(new Date());
        return topicRepository.save(createTopic);
    }

    @Override
    public Topic readTopic(String id) {
        return topicRepository.findById(id).orElse(null);
    }

    @Override
    public Topic updateTopic(TopicDTO topicDTO) {
        Topic topic = readTopic(topicDTO.getId());
        if (topic == null){
            return null;
        }
        topic.setName(topicDTO.getName());
        topic.setDescription(topicDTO.getDescription());
        return topicRepository.save(topic);
    }

    @Override
    public Topic deleteTopic(String id) {
        Topic topicDelete = readTopic(id);
        if (topicDelete == null){
            return null;
        }
        topicRepository.deleteById(id);
        return topicDelete;
    }

    //business
    @Override
    public Topic readTopicByName(String name) {
        return topicRepository.findByName(name).orElse(null);
    }
}
