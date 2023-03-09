package com.rafael.app.blogru.modules.posts.models;

import com.rafael.app.blogru.security.document.Role;
import com.rafael.app.blogru.security.document.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.Set;

public class CommentModel {

    private String id;
    private String content;
    private String commentatorId;
    private Date registerDate;


}
