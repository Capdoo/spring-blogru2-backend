package com.rafael.app.blogru.modules.comments;

import com.rafael.app.blogru.modules.comments.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentsRepository extends MongoRepository<Comments, String> {

}
