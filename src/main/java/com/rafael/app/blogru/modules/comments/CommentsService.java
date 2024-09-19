package com.rafael.app.blogru.modules.comments;

import com.rafael.app.blogru.modules.comments.Comments;
import com.rafael.app.blogru.modules.comments.CommentDTO;
import com.rafael.app.blogru.modules.comments.CommentModel;

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
