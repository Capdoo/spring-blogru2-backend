package com.rafael.app.blogru.modules.comments.repository;

import com.rafael.app.blogru.modules.comments.document.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentsRepository extends MongoRepository<Comments, String> {

}
