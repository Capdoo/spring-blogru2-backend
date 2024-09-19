package com.rafael.app.blogru.modules.sections;

import com.rafael.app.blogru.modules.sections.Section;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SectionRepository extends MongoRepository<Section, String> {

}
