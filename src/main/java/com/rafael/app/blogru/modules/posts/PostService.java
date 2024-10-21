package com.rafael.app.blogru.modules.posts;

import com.rafael.app.blogru.modules.posts.Post;
import com.rafael.app.blogru.modules.posts.PostDto;
import com.rafael.app.blogru.security.document.User;

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
    List<Post> readByUser(User user);
}
