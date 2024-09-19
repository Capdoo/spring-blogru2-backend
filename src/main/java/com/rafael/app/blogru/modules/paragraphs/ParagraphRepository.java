package com.rafael.app.blogru.modules.paragraphs;

import com.rafael.app.blogru.modules.paragraphs.Paragraph;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParagraphRepository extends MongoRepository<Paragraph, String> {
}
