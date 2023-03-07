package com.rafael.app.blogru.repository;

import com.rafael.app.blogru.document.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {

}
