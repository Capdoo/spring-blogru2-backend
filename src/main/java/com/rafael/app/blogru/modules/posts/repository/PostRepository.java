package com.rafael.app.blogru.modules.posts.repository;

import com.rafael.app.blogru.modules.posts.document.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

    Optional<Post> findByTitle(String title);

}
