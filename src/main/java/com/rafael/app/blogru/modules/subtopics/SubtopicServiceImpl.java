package com.rafael.app.blogru.modules.subtopics;

import com.rafael.app.blogru.modules.topics.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SubtopicServiceImpl implements SubtopicService {

    @Autowired
    SubtopicRepository subtopicRepository;

    @Override
    public List<Subtopic> readAllSubtopics() {
        return subtopicRepository.findAll();
    }

    @Override
    public Subtopic createSubtopic(SubtopicDto subtopicDTO) {
        Subtopic subtopic = new Subtopic();
        subtopic.setName(subtopicDTO.getName());
        subtopic.setImage(subtopicDTO.getDescription());
        subtopic.setRegisterDate(new Date());
        return subtopicRepository.save(subtopic);
    }

    @Override
    public Subtopic readSubtopic(String id) {
        return subtopicRepository.findById(id).orElse(null);
    }

    @Override
    public Subtopic updateSubtopic(SubtopicDto subtopicDTO) {
        Subtopic subtopicDB = readSubtopic(subtopicDTO.getId());
        if (subtopicDB == null){
            return null;
        }
        subtopicDB.setName(subtopicDTO.getName());
        subtopicDB.setImage(subtopicDTO.getDescription());
        return subtopicRepository.save(subtopicDB);
    }

    @Override
    public Subtopic deleteSubtopic(String id) {
        Subtopic subtopicDelete = readSubtopic(id);
        if (subtopicDelete == null){
            return null;
        }
        subtopicRepository.deleteById(id);
        return subtopicDelete;
    }

    //business
    @Override
    public Subtopic readSubtopicByName(String name) {
        return subtopicRepository.findByName(name).orElse(null);
    }

    @Override
    public List<Subtopic> readSubtopicsByTopic(Topic topic) {
        List<Subtopic> listSubtopics;

        return subtopicRepository.findAllByTopic(topic);
    }
}
