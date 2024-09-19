package com.rafael.app.blogru.modules.topics;

import com.rafael.app.blogru.modules.topics.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TopicRepository extends MongoRepository<Topic, String> {

    Optional<Topic> findByName(String name);

}
