package com.rafael.app.blogru.modules.comments.service;

import com.rafael.app.blogru.modules.comments.document.Comments;
import com.rafael.app.blogru.modules.comments.dto.CommentDTO;
import com.rafael.app.blogru.modules.comments.models.CommentModel;
import com.rafael.app.blogru.modules.comments.repository.CommentsRepository;
import com.rafael.app.blogru.modules.posts.document.Post;
import com.rafael.app.blogru.modules.posts.repository.PostRepository;
import com.rafael.app.blogru.modules.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService{

    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Override
    public CommentModel createComment(CommentDTO commentDTO) {

        Post post = postService.readPost(commentDTO.getPost_id());
        CommentModel newComment = new CommentModel();
            newComment.setContent(commentDTO.getContent());
            newComment.setRegisterDate(new Date());
            newComment.setUserId(commentDTO.getUser_id());

        if (post.getComments() == null){
            List<CommentModel> newListOfComments = new ArrayList<>();
            newComment.setId(0);
            newListOfComments.add(newComment);

            Comments newCollectionOfComments = Comments.builder()
                    .registerDate(new Date())
                    .commentsList(newListOfComments)
                    .build();
            Comments comments = commentsRepository.save(newCollectionOfComments);
            post.setComments(comments);
            postRepository.save(post);
            return newComment;
        }
        //add new comment to existing collection of comments
        Integer nextIndex = post.getComments().getCommentsList().size();
        newComment.setId(nextIndex);
        List<CommentModel> previousListOfComments = post.getComments().getCommentsList();
        previousListOfComments.add(newComment);

        Comments originalCommentsCollection = post.getComments();
        originalCommentsCollection.setCommentsList(previousListOfComments);

        commentsRepository.save(originalCommentsCollection);
        return newComment;
    }

    @Override
    public CommentModel readComment(String commentsId, String commentId) {
        return null;
    }

    @Override
    public CommentModel updateComment(CommentDTO commentDTO) {
        return null;
    }

    @Override
    public CommentModel deleteComment(String commentsId, String commentId) {
        return null;
    }

    @Override
    public List<CommentModel> readCommentsByPostId(String postId) {
        return null;
    }
}
