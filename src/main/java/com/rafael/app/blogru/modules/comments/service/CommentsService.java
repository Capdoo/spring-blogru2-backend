package com.rafael.app.blogru.modules.comments.service;

import com.rafael.app.blogru.modules.comments.document.Comments;
import com.rafael.app.blogru.modules.comments.dto.CommentDTO;
import com.rafael.app.blogru.modules.comments.models.CommentModel;

import java.util.List;

public interface CommentsService {

    CommentModel createComment(CommentDTO commentDTO);
    CommentModel readComment(String postId, Integer commentId);

    CommentModel updateComment(CommentDTO commentDTO, Integer commentId);
    CommentModel deleteComment(String postId, Integer commentId);

    //Business
    List<CommentModel> readCommentsByPostId(String postId);
    Comments readCommentsCollection(String id);

}
