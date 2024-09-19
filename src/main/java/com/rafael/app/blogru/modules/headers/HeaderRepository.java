package com.rafael.app.blogru.modules.headers;

import com.rafael.app.blogru.modules.headers.Header;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeaderRepository extends MongoRepository<Header, String> {
}
