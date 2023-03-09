package com.rafael.app.blogru.modules.posts.document;

import com.rafael.app.blogru.modules.topics.document.Topic;
import com.rafael.app.blogru.security.document.User;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Document
@Data
public class Post {

    private String id;
    private String title;
    private String resume;
    @DocumentReference
    private Topic topic;
    @DocumentReference
    private User user;
    private Date registerDate;

}
