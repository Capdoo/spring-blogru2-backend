package com.rafael.app.blogru.modules.posts.service;

import com.rafael.app.blogru.modules.posts.document.Post;
import com.rafael.app.blogru.modules.posts.dto.PostDTO;
import com.rafael.app.blogru.modules.posts.repository.PostRepository;
import com.rafael.app.blogru.modules.subtopics.document.Subtopic;
import com.rafael.app.blogru.modules.subtopics.service.SubtopicService;
import com.rafael.app.blogru.modules.topics.document.Topic;
import com.rafael.app.blogru.modules.topics.service.TopicService;
import com.rafael.app.blogru.security.document.User;
import com.rafael.app.blogru.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;
    @Autowired
    TopicService topicService;
    @Autowired
    SubtopicService subtopicService;
    @Autowired
    UserService userService;

    @Override
    public List<Post> readAllPosts() {
        return postRepository.findAll();
    }

    //First time
    @Override
    public Post createPost(PostDTO postDTO) {
        Topic topicSelected = topicService.readTopic(postDTO.getTopic_id());
        Subtopic subtopicSelected = subtopicService.readSubtopic(postDTO.getSubtopic_id());
        User userCreator = userService.findById(postDTO.getUser_id());
        Post postCreate = Post.builder()
                .title(postDTO.getTitle())
                .summary(postDTO.getSummary())
                .topic(topicSelected)
                .subtopic(subtopicSelected)
                .user(userCreator)
                .registerDate(new Date())
                //content
                .headers(postDTO.getHeaders())
                .paragraphs(postDTO.getParagraphs())
                .build();
        return postRepository.save(postCreate);
    }

    @Override
    public Post readPost(String id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post updatePost(PostDTO postDTO) {
        Post postDB = readPost(postDTO.getId());
        if (postDB == null){
            return null;
        }
        //general
        Topic topicUpdate = topicService.readTopic(postDTO.getTopic_id());
        Subtopic subtopicUpdate = subtopicService.readSubtopic(postDTO.getSubtopic_id());
        postDB.setTitle(postDB.getTitle());
        postDB.setSummary(postDB.getSummary());
        postDB.setTopic(topicUpdate);
        postDB.setSubtopic(subtopicUpdate);
        //content
        postDB.setHeaders(postDTO.getHeaders());
        postDB.setParagraphs(postDTO.getParagraphs());
        return postRepository.save(postDB);
    }

    @Override
    public Post deletePost(String id) {
        Post postDB = readPost(id);
        if (postDB == null){
            return null;
        }
        postRepository.deleteById(id);
        return postDB;
    }

    //business
    @Override
    public Post readByTitle(String title) {
        return postRepository.findByTitle(title).orElse(null);
    }

}
