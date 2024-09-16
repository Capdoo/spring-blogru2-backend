package com.rafael.app.blogru.modules.posts.service;

import com.rafael.app.blogru.modules.posts.document.Post;
import com.rafael.app.blogru.modules.posts.dto.PostDto;

import java.util.List;

public interface PostService {
    List<Post> readAllPosts();
    //crud
    Post createPost(PostDto postDTO);
    Post readPost(String id);
    Post updatePost(PostDto postDTO);
    Post deletePost(String id);
    //business ...
    Post readByTitle(String title);
}
