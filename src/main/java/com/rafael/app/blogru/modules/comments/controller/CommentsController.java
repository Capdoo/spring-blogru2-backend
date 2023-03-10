package com.rafael.app.blogru.modules.comments.controller;

import com.rafael.app.blogru.modules.comments.dto.CommentDTO;
import com.rafael.app.blogru.modules.comments.models.CommentModel;
import com.rafael.app.blogru.modules.comments.service.CommentsService;
import com.rafael.app.blogru.modules.posts.document.Post;
import com.rafael.app.blogru.modules.posts.service.PostService;
import com.rafael.app.blogru.security.document.User;
import com.rafael.app.blogru.security.jwt.JwtHelper;
import com.rafael.app.blogru.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    @Autowired
    CommentsService commentsService;
    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('creator')")
    @PostMapping
    ResponseEntity<Object> createComment(@RequestHeader(value = "Authorization") String token, @RequestBody CommentDTO commentDTO){

        String userId = jwtHelper.getUserIdFromAccessToken(token.split(" ")[1]);
        commentDTO.setUser_id(userId);
        User user = userService.findById(userId);
        if (user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
        Post post = postService.readPost(commentDTO.getPost_id());
        if (post == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        CommentModel createComment = commentsService.createComment(commentDTO);
        return ResponseEntity.ok().body(createComment);

    }








}
