package com.rafael.app.blogru.modules.comments;

import com.rafael.app.blogru.modules.posts.Post;
import com.rafael.app.blogru.modules.posts.PostService;
import com.rafael.app.blogru.security.document.User;
import com.rafael.app.blogru.security.jwt.JwtHelper;
import com.rafael.app.blogru.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentResource {
    @Autowired
    CommentService commentService;
    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;


    ResponseEntity<Object> createComment(@RequestHeader(value = "Authorization") String token, @RequestBody CommentDto commentDTO){
        String userId;
        User user;
        Post post;
        Comment createComment;

        userId = jwtHelper.getUserIdFromAccessToken(token.split(" ")[1]);
        commentDTO.setUserId(userId);
        user = userService.findById(userId);
        if (user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
        post = postService.readPost(commentDTO.getPostId());
        if (post == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        createComment = commentService.createComment(commentDTO);
        return ResponseEntity.ok().body(CommentMapper.mapCommentDto(createComment));
    }

    ResponseEntity<Object> readComment(@PathVariable(value = "id") String id){
        Comment comment;

        comment = commentService.readComment(id);
        if (comment == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment does not exists");
        }

        return ResponseEntity.ok().body(CommentMapper.mapCommentDto(comment));
    }

    ResponseEntity<Object> readCommentByPostId(@PathVariable(value = "postId") String postId){
        Post post;
        List<Comment> listComments;
        List<CommentDto> listCommentsDto;

        post = postService.readPost(postId);
        if (post == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        listComments = post.getListComments();
        listCommentsDto = listComments.stream()
                .map(CommentMapper::mapCommentDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listCommentsDto);
    }

//    ResponseEntity<Object> readCommentByPostIdAndCommentId(@PathVariable(value = "id") String postId, @RequestParam(value = "id") Integer id){
//        Post post;
//        CommentModel commentTarget;
//
//        post = postService.readPost(postId);
//        if (post == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
//        }
//        if (post.getComment() == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No comment found");
//        }
//        commentTarget = commentService.readComment(postId, id);
//        if (commentTarget == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment not found");
//        }
//        return ResponseEntity.ok().body(commentTarget);
//    }
//
//    ResponseEntity<Object> updateComment(@PathVariable(value = "id") String postId, @RequestParam(value = "id") Integer id, @RequestBody CommentDto commentDTO){
//        Post post;
//        CommentModel commentUpdate;
//        post = postService.readPost(postId);
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
//        commentUpdate = commentService.updateComment(commentDTO, id);
//        return ResponseEntity.ok().body(commentUpdate);
//    }
//
//    ResponseEntity<Object> deleteComment(@PathVariable(value = "id") String postId, @RequestParam(value = "id") Integer id){
//        Post post;
//        CommentModel commentTarget;
//        CommentModel commentDelete;
//
//        post = postService.readPost(postId);
//
//        if (post == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
//        }
//        if (post.getComment() == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No comment found");
//        }
//        commentTarget = commentService.readComment(postId, id);
//        if (commentTarget == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment not found");
//        }
//        commentDelete = commentService.deleteComment(postId, id);
//
//        return ResponseEntity.ok().body(commentDelete);
//    }
}
