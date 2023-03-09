package com.rafael.app.blogru.modules.posts.service;

import com.rafael.app.blogru.modules.posts.document.Post;
import com.rafael.app.blogru.modules.posts.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<Post> readAllPosts();
    //crud
    Post createPost(PostDTO postDTO);
    Post readPost(String id);
    Post updatePost(PostDTO postDTO);
    Post deletePost(String id);
    //business ...
    Post readByTitle(String title);
}
