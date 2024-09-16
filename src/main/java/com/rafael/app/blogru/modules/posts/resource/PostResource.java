package com.rafael.app.blogru.modules.posts.resource;

import com.rafael.app.blogru.modules.posts.document.Post;
import com.rafael.app.blogru.modules.posts.dto.PostDto;
import com.rafael.app.blogru.modules.posts.mapper.PostMapper;
import com.rafael.app.blogru.modules.posts.service.PostService;
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
        List<Post> listPostDB = postService.readAllPosts();
        List<PostDto> listPostDto = listPostDB.stream()
                .map(PostMapper::mapPostDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listPostDto);
    }

    public ResponseEntity<Object> createPost(@RequestHeader("Authorization") String token, @RequestBody PostDto postDTO) {
        //Set creator id
        String user_id = jwtHelper.getUserIdFromAccessToken(token.split(" ")[1]);
        postDTO.setUser_id(user_id);
        User userSelected = userService.findById(user_id);
        if (userSelected == null) {  //Remove
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exists");
        }

        Post postDB = postService.readByTitle(postDTO.getTitle());
        if (postDB != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another post with the same title already exists");
        }
        Post postCreate = postService.createPost(postDTO);
        return ResponseEntity.ok().body(PostMapper.mapPostDto(postCreate));
    }

    public ResponseEntity<Object> readPostById(@PathVariable(value = "id") String id) {
        Post postDB = postService.readPost(id);
        if (postDB == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        return ResponseEntity.ok().body(PostMapper.mapPostDto(postDB));
    }

    public ResponseEntity<Object> updatePost(@PathVariable(value = "id") String id, @RequestBody PostDto postDTO) {
        Post postDB = postService.readPost(id);
        if (postDB == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        postDTO.setId(id);
        Post postUpdate = postService.updatePost(postDTO);
        return ResponseEntity.ok().body(PostMapper.mapPostDto(postUpdate));
    }

    public ResponseEntity<Object> deletePost(@PathVariable(value = "id") String id) {
        Post postDB = postService.readPost(id);
        if (postDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        Post postDelete = postService.deletePost(id);
        return ResponseEntity.ok().body(PostMapper.mapPostDto(postDelete));
    }

}