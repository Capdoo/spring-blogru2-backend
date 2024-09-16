package com.rafael.app.blogru.modules.paragraphs.repository;

import com.rafael.app.blogru.modules.paragraphs.document.Paragraph;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParagraphRepository extends MongoRepository<Paragraph, String> {
}
