package com.rafael.app.blogru.modules.headers.repository;

import com.rafael.app.blogru.modules.headers.document.Header;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeaderRepository extends MongoRepository<Header, String> {
}
