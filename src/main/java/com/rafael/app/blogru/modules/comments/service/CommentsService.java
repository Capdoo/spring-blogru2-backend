package com.rafael.app.blogru.modules.comments.service;

import com.rafael.app.blogru.modules.comments.dto.CommentDTO;
import com.rafael.app.blogru.modules.comments.models.CommentModel;

import java.util.List;

public interface CommentsService {

    CommentModel createComment(CommentDTO commentDTO);
    CommentModel readComment(String commentsId, String commentId);
    CommentModel updateComment(CommentDTO commentDTO);
    CommentModel deleteComment(String commentsId, String commentId);

    //Business
    List<CommentModel> readCommentsByPostId(String postId);

}
