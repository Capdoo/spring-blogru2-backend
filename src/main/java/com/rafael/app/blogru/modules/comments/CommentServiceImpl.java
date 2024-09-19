package com.rafael.app.blogru.modules.comments;

import com.rafael.app.blogru.modules.posts.Post;
import com.rafael.app.blogru.modules.posts.PostRepository;
import com.rafael.app.blogru.modules.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Override
    public Comment createComment(CommentDto commentDto) {
        Post post;
        Comment commentCreate;
        Comment commentParent;
        List<Comment> listComments;
        List<Comment> listCommentsParent;

        //Coment comment
        if (commentDto.getCommentParentId() != null) {
            commentCreate = Comment.builder()
                .content(commentDto.getContent())
                .registerDate(new Date())
                .build();
            commentCreate = commentRepository.save(commentCreate);

            commentParent = readComment(commentDto.getCommentParentId());
            if (commentParent == null) {
                return null;
            }

            if (commentParent.getListComments() == null) {
                commentParent.setListComments(new ArrayList<>());
            }
            listCommentsParent = commentParent.getListComments();
            listCommentsParent.add(commentCreate);
            commentRepository.save(commentParent);

            return commentCreate;
        }

        //Post comment
        commentCreate = Comment.builder()
                .content(commentDto.getContent())
                .build();

        commentCreate = commentRepository.save(commentCreate);

        post = postService.readPost(commentDto.getPostId());
        if (post == null) {
            return null;
        }

        if (post.getListComments() == null) {
            post.setListComments(new ArrayList<>());
        }

        listComments = post.getListComments();
        listComments.add(commentCreate);

//        post.setComment(commentCreate);
        post.setListComments(listComments);
        postRepository.save(post);

        return commentCreate;
    }

    @Override
    public Comment readComment(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment updateComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public Comment deleteComment(String id) {
        return null;
    }

    @Override
    public List<Comment> readCommentsByPostId(String postId) {
        return null;
    }

}
