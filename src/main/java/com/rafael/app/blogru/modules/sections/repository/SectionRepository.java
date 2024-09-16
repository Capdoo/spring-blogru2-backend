package com.rafael.app.blogru.modules.sections.repository;

import com.rafael.app.blogru.modules.sections.document.Section;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SectionRepository extends MongoRepository<Section, String> {

//    Optional<Section> findByTitle(String title);
    Optional<Section> findById(String id);
}
