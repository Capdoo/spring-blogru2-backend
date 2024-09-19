package com.rafael.app.blogru.modules.subtopics;

import com.rafael.app.blogru.modules.subtopics.Subtopic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubtopicRepository extends MongoRepository<Subtopic, String> {

    Optional<Subtopic> findByName(String name);

}
