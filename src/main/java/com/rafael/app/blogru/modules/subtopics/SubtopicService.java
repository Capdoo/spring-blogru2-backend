package com.rafael.app.blogru.modules.subtopics;

import com.rafael.app.blogru.modules.topics.Topic;

import java.util.List;

public interface SubtopicService {

    List<Subtopic> readAllSubtopics();

    //crud
    Subtopic createSubtopic(SubtopicDto subtopicDTO);
    Subtopic readSubtopic(String id);
    Subtopic updateSubtopic(SubtopicDto subtopicDTO);
    Subtopic deleteSubtopic(String id);

    //business
    Subtopic readSubtopicByName(String name);
    List<Subtopic> readSubtopicsByTopic(Topic topic);
}
