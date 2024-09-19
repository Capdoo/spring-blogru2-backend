package com.rafael.app.blogru.modules.comments;

import com.rafael.app.blogru.modules.posts.Post;
import com.rafael.app.blogru.modules.posts.PostService;
import com.rafael.app.blogru.security.document.User;
import com.rafael.app.blogru.security.jwt.JwtHelper;
import com.rafael.app.blogru.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user') or hasAuthority('creator')")
    @GetMapping("/{id}")
    ResponseEntity<Object> getAllComentsByPostId(@PathVariable(value = "id") String postId){
        Post post = postService.readPost(postId);
        if (post == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        List<CommentModel> listCommentsFromPost = commentsService.readCommentsByPostId(postId);
        return ResponseEntity.ok().body(listCommentsFromPost);
    }

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

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user') or hasAuthority('creator')")
    @GetMapping("/single/{id}")
    ResponseEntity<Object> getComentByPostIdAndCommentId(@PathVariable(value = "id") String postId, @RequestParam(value = "id") Integer id){
        Post post = postService.readPost(postId);
        if (post == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        if (post.getComments() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No comments found");
        }
        CommentModel commentTarget = commentsService.readComment(postId, id);
        if (commentTarget == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment not found");
        }
        return ResponseEntity.ok().body(commentTarget);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('creator')")
    @PutMapping("/{id}")
    ResponseEntity<Object> updateComment(@PathVariable(value = "id") String postId, @RequestParam(value = "id") Integer id, @RequestBody CommentDTO commentDTO){
        Post post = postService.readPost(postId);
        if (post == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        if (post.getComments() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No comments found");
        }
        CommentModel commentTarget = commentsService.readComment(postId, id);
        if (commentTarget == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment not found");
        }
        commentDTO.setPost_id(postId);
        CommentModel commentUpdate = commentsService.updateComment(commentDTO, id);
        return ResponseEntity.ok().body(commentUpdate);
    }


    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('creator')")
    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteComment(@PathVariable(value = "id") String postId, @RequestParam(value = "id") Integer id){
        Post post = postService.readPost(postId);
        if (post == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        if (post.getComments() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No comments found");
        }
        CommentModel commentTarget = commentsService.readComment(postId, id);
        if (commentTarget == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment not found");
        }
        CommentModel commentDelete = commentsService.deleteComment(postId, id);
        return ResponseEntity.ok().body(commentDelete);
    }





    }
