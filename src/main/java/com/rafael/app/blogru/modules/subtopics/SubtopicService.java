package com.rafael.app.blogru.modules.subtopics;

import com.rafael.app.blogru.modules.subtopics.Subtopic;
import com.rafael.app.blogru.modules.subtopics.SubtopicDTO;

import java.util.List;

public interface SubtopicService {

    List<Subtopic> readAllSubtopics();

    //crud
    Subtopic createSubtopic(SubtopicDTO subtopicDTO);
    Subtopic readSubtopic(String id);
    Subtopic updateSubtopic(SubtopicDTO subtopicDTO);
    Subtopic deleteSubtopic(String id);

    //business
    Subtopic readSubtopicByName(String name);
}
