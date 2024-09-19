package com.rafael.app.blogru.modules.comments;

import com.rafael.app.blogru.modules.posts.Post;
import com.rafael.app.blogru.modules.posts.PostRepository;
import com.rafael.app.blogru.modules.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

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
    public CommentModel readComment(String postId, Integer commentId) {
        Comments comments = postService.readPost(postId).getComments();
        List<CommentModel> sendList = comments.getCommentsList().stream()
                .filter( comment -> Objects.equals(comment.getId(), commentId))
                .collect(Collectors.toList());
        return sendList.size() == 0 ? null : sendList.get(0);
    }

    @Override
    public CommentModel updateComment(CommentDTO commentDTO, Integer commentId) {
        Post post = postService.readPost(commentDTO.getPost_id());
        Comments comments = post.getComments();
        List<CommentModel> listCommentsOriginal = comments.getCommentsList();

        List<CommentModel> auxiliar = listCommentsOriginal.stream().filter( comment -> comment.getId().equals(commentId)).collect(Collectors.toList());
        CommentModel target = auxiliar.get(0);
        target.setContent(commentDTO.getContent());
        //Since is ordered
        listCommentsOriginal.set(commentId, target);

        comments.setCommentsList(listCommentsOriginal);
        commentsRepository.save(comments);

        return target;
    }

    @Override
    public CommentModel deleteComment(String postId, Integer commentId) {
        Post post = postService.readPost(postId);
        Comments comments = post.getComments();
        List<CommentModel> auxiliar = comments.getCommentsList().stream().filter( comment -> comment.getId().equals(commentId)).collect(Collectors.toList());
        List<CommentModel> updateListComments = comments.getCommentsList().stream().filter( value -> !Objects.equals(value.getId(), commentId)).collect(Collectors.toList());
        comments.setCommentsList(updateListComments);
        commentsRepository.save(comments);
        return auxiliar.size() == 0 ? null : auxiliar.get(0);
    }

    @Override
    public List<CommentModel> readCommentsByPostId(String postId) {
        Post post = postService.readPost(postId);
        return post.getComments().getCommentsList();
    }

    @Override
    public Comments readCommentsCollection(String id) {
        return commentsRepository.findById(id).orElse(null);
    }



}
