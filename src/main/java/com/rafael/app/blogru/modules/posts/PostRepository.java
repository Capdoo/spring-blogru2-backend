package com.rafael.app.blogru.modules.posts;

import com.rafael.app.blogru.modules.posts.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

    Optional<Post> findByTitle(String title);

}
