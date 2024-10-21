package com.rafael.app.blogru.modules.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostResource postResource;

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user')")
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createPost(@RequestHeader("Authorization") String token, @RequestBody PostDto postDTO){
        return postResource.createPost(token, postDTO);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user')")
    @GetMapping("/read")
    public ResponseEntity<Object> readPosts(){
        return postResource.readPosts();
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user')")
    @GetMapping("/read/{id}")
    public ResponseEntity<Object> readPost(@PathVariable(value = "id") String id){
        return postResource.readPostById(id);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user')")
    @GetMapping("/read/user")
    public ResponseEntity<Object> readPostsByUserId(@RequestHeader("Authorization") String token){
        return postResource.readPostsByUserId(token);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable(value = "id") String id, @RequestBody PostDto postDTO){
        return postResource.updatePost(id, postDTO);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable(value = "id") String id){
        return postResource.deletePost(id);
    }
}
