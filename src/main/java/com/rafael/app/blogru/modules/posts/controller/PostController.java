package com.rafael.app.blogru.modules.posts.controller;

import com.rafael.app.blogru.modules.posts.document.Post;
import com.rafael.app.blogru.modules.posts.dto.PostDTO;
import com.rafael.app.blogru.modules.posts.service.PostService;
import com.rafael.app.blogru.security.document.User;
import com.rafael.app.blogru.security.jwt.JwtHelper;
import com.rafael.app.blogru.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    JwtHelper jwtHelper;

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user') or hasAuthority('creator')")
    @GetMapping
    public ResponseEntity<Object> getAllPosts(){
        List<Post> listPostDB = postService.readAllPosts();
        List<PostDTO> listPostDTO = listPostDB.stream()
                .map(this::convertPostToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listPostDTO);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('creator')")
    @PostMapping
    public ResponseEntity<Object> registerPost(@RequestHeader("Authorization") String token, @RequestBody PostDTO postDTO){
        //Set creator id
        String user_id = jwtHelper.getUserIdFromAccessToken(token.split(" ")[1]);
        postDTO.setUser_id(user_id);
        User userSelected = userService.findById(user_id);
        if (userSelected == null){  //Remove
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exists");
        }

        Post postDB = postService.readByTitle(postDTO.getTitle());
        if (postDB != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another post with the same title already exists");
        }
        Post postCreate = postService.createPost(postDTO);
        return ResponseEntity.ok().body(this.convertPostToDTO(postCreate));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('user') or hasAuthority('creator')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable(value = "id") String id){
        Post postDB = postService.readPost(id);
        if (postDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        return ResponseEntity.ok().body(this.convertPostToDTO(postDB));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('creator')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable(value = "id") String id, @RequestBody PostDTO postDTO){
        Post postDB = postService.readPost(id);
        if (postDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        postDTO.setId(id);
        Post postUpdate = postService.updatePost(postDTO);
        return ResponseEntity.ok().body(this.convertPostToDTO(postUpdate));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin') or hasAuthority('creator')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable(value = "id") String id){
        Post postDB = postService.readPost(id);
        if (postDB == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exists");
        }
        Post postDelete = postService.deletePost(id);
        return ResponseEntity.ok().body(this.convertPostToDTO(postDelete));
    }

    private PostDTO convertPostToDTO(Post post){
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .summary(post.getSummary())
                .topic_id(post.getTopic().getId())
                .subtopic_id(post.getSubtopic().getId())
                .user_id(post.getUser().getId())
                .register_date(post.getRegisterDate().toString())
                .headers(post.getHeaders())
                .paragraphs(post.getParagraphs())
                .build();
    }

}
