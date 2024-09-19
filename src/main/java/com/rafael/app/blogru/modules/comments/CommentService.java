package com.rafael.app.blogru.modules.comments;

import java.util.List;

public interface CommentService {

    Comment createComment(CommentDto commentDTO);
    Comment readComment(String id);
    Comment updateComment(CommentDto commentDto);
    Comment deleteComment(String id);
    //Business
    List<Comment> readCommentsByPostId(String postId);

}
