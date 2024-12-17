package com.rafael.app.blogru.modules.posts;

import com.rafael.app.blogru.security.document.User;
import com.rafael.app.blogru.security.jwt.JwtHelper;
import com.rafael.app.blogru.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostResource {

    @Autowired
    PostService postService;
    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    UserService userService;

    public ResponseEntity<Object> readPosts() {
        List<Post> listPostDB;
        List<PostDto> listPostDto;

        listPostDB = postService.readAllPosts().stream()
                .filter(e -> e.getEstId().equals(new BigDecimal(1)))
                .collect(Collectors.toList());

//        listPostDto = listPostDB.stream()
//                .map(e -> {
//                    User user
//                    e.setUser();
//                })

        listPostDto = listPostDB.stream()
                .map(PostMapper::mapPostDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listPostDto);
    }

    public ResponseEntity<Object> createPost(String token, PostDto postDTO) {
        Post postDb;
        Post postCreate;
        String user_id;
        User userSelected;

        //Set creator id
        user_id = jwtHelper.getUserIdFromAccessToken(token.split(" ")[1]);
        postDTO.setUserId(user_id);
        userSelected = userService.findById(user_id);
        if (userSelected == null) {  //Remove
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exists");
        }

        postDb = postService.readByTitle(postDTO.getTitle());
        if (postDb != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another post with the same title already exists");
        }
        postCreate = postService.createPost(postDTO);
        return ResponseEntity.ok().body(PostMapper.mapPostDto(postCreate));
    }

    public ResponseEntity<Object> readPostsByUserId(String token) {
        String userId;
        User user;
        List<Post> listPosts;
        List<PostDto> listPostsDto;

        userId = jwtHelper.getUserIdFromAccessToken(token.split(" ")[1]);
        user = userService.findById(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }

        listPosts = postService.readByUser(user);

//        if (listPosts.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No posts were found");
//        }

        listPostsDto = listPosts.stream()
                .filter(e -> e.getEstId().equals(new BigDecimal(1)))
                .map(PostMapper::mapPostDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listPostsDto);
    }

    public ResponseEntity<Object> readPostsByTopicId(String topicId) {
        String userId;
        User user;
        List<Post> listPosts;
        List<PostDto> listPostsDto;

//        userId = jwtHelper.getUserIdFromAccessToken(token.split(" ")[1]);
//        user = userService.findById(userId);
//        if (user == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
//        }

//        listPosts = postService.readByUser(user);

        listPostsDto = postService.readAllPosts().stream()
                .filter(e -> e.getEstId().equals(new BigDecimal(1)))
                .filter(e -> e.getTopic().getId().equals(topicId))
                .map(PostMapper::mapPostDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listPostsDto);
    }

    public ResponseEntity<Object> readPostsBySubTopicId(String subTopicId) {
        String userId;
        User user;
        List<Post> listPosts;
        List<PostDto> listPostsDto;

        listPostsDto = postService.readAllPosts().stream()
                .filter(e -> e.getEstId().equals(new BigDecimal(1)))
                .filter(e -> e.getSubtopic().getId().equals(subTopicId))
                .map(PostMapper::mapPostDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listPostsDto);
    }

    public ResponseEntity<Object> readPostById(@PathVariable(value = "id") String id) {
        Post postDb;

        postDb = postService.readPost(id);
        if (postDb == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        return ResponseEntity.ok().body(PostMapper.mapPostDto(postDb));
    }



    public ResponseEntity<Object> updatePost(@PathVariable(value = "id") String id, @RequestBody PostDto postDTO) {
        Post postDb;
        Post postUpdate;

        postDb = postService.readPost(id);
        if (postDb == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A problem ocurred");
        }
        postDTO.setId(id);
        postUpdate = postService.updatePost(postDTO);
        return ResponseEntity.ok().body(PostMapper.mapPostDto(postUpdate));
    }

    public ResponseEntity<Object> deletePost(@PathVariable(value = "id") String id) {
        Post postDb;

        postDb = postService.readPost(id);
        if (postDb == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        Post postDelete = postService.deletePost(id);
        return ResponseEntity.ok().body(PostMapper.mapPostDto(postDelete));
    }

}