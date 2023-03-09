package com.rafael.app.blogru.modules.subtopics.service;

import com.rafael.app.blogru.modules.subtopics.document.Subtopic;
import com.rafael.app.blogru.modules.subtopics.dto.SubtopicDTO;

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
