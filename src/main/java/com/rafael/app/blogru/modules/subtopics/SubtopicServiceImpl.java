package com.rafael.app.blogru.modules.subtopics;

import com.rafael.app.blogru.modules.subtopics.Subtopic;
import com.rafael.app.blogru.modules.subtopics.SubtopicDTO;
import com.rafael.app.blogru.modules.subtopics.SubtopicRepository;
import com.rafael.app.blogru.modules.subtopics.SubtopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Subtopic createSubtopic(SubtopicDTO subtopicDTO) {
        Subtopic subtopic = new Subtopic();
        subtopic.setName(subtopicDTO.getName());
        subtopic.setDescription(subtopicDTO.getDescription());
        subtopic.setRegisterDate(new Date());
        return subtopicRepository.save(subtopic);
    }

    @Override
    public Subtopic readSubtopic(String id) {
        return subtopicRepository.findById(id).orElse(null);
    }

    @Override
    public Subtopic updateSubtopic(SubtopicDTO subtopicDTO) {
        Subtopic subtopicDB = readSubtopic(subtopicDTO.getId());
        if (subtopicDB == null){
            return null;
        }
        subtopicDB.setName(subtopicDTO.getName());
        subtopicDB.setDescription(subtopicDTO.getDescription());
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
}
