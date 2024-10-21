package com.rafael.app.blogru.modules.posts;

import com.rafael.app.blogru.modules.posts.Post;
import com.rafael.app.blogru.security.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

    Optional<Post> findByTitle(String title);

    List<Post> findAllByUser(User user);

}