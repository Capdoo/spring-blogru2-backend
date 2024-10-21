package com.rafael.app.blogru.modules.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentResource commentResource;



    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user')")
    @PostMapping
    ResponseEntity<Object> createComment(@RequestHeader(value = "Authorization") String token, @RequestBody CommentDto commentDTO){
        return commentResource.createComment(token, commentDTO);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user')")
    @GetMapping("/read/{id}")
    ResponseEntity<Object> getComentByPostIdAndCommentId(@PathVariable(value = "id") String id){
        return commentResource.readComment(id);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user')")
    @GetMapping("/read/post/{id}")
    ResponseEntity<Object> getAllComentsByPostId(@PathVariable(value = "id") String id){
        return commentResource.readCommentByPostId(id);
    }
}