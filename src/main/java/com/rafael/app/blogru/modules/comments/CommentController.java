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



    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('creator') or hasAuthority('user')")
    @PostMapping
    ResponseEntity<Object> createComment(@RequestHeader(value = "Authorization") String token, @RequestBody CommentDto commentDTO){
        return commentResource.createComment(token, commentDTO);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user') or hasAuthority('creator')")
    @GetMapping("/read/{id}")
    ResponseEntity<Object> getComentByPostIdAndCommentId(@PathVariable(value = "id") String id){
        return commentResource.readComment(id);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user') or hasAuthority('creator')")
    @GetMapping("/read/post/{id}")
    ResponseEntity<Object> getAllComentsByPostId(@PathVariable(value = "id") String id){
        return commentResource.readCommentByPostId(id);
    }

//    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('creator')")
//    @PutMapping("/{id}")
//    ResponseEntity<Object> updateComment(@PathVariable(value = "id") String postId, @RequestParam(value = "id") Integer id, @RequestBody CommentDto commentDTO){
//        Post post = postService.readPost(postId);
//        if (post == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
//        }
//        if (post.getComment() == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No comment found");
//        }
//        CommentModel commentTarget = commentService.readComment(postId, id);
//        if (commentTarget == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment not found");
//        }
//        commentDTO.setPost_id(postId);
//        CommentModel commentUpdate = commentService.updateComment(commentDTO, id);
//        return ResponseEntity.ok().body(commentUpdate);
//    }
//
//
//    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('creator')")
//    @DeleteMapping("/{id}")
//    ResponseEntity<Object> deleteComment(@PathVariable(value = "id") String postId, @RequestParam(value = "id") Integer id){
//        return commentResource.deleteComment(postId, id);
//    }

}