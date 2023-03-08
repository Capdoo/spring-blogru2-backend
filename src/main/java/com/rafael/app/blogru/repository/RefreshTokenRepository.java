package com.rafael.app.blogru.repository;

import com.rafael.app.blogru.document.RefreshToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    void deleteByOwner_Id(Object id);
    default void deleteByOwner_Id(String id){
        deleteByOwner_Id(new ObjectId(id));
    };
}
