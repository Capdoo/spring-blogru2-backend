package com.rafael.app.blogru.modules.comments.models;

import com.rafael.app.blogru.security.document.Role;
import com.rafael.app.blogru.security.document.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class CommentModel {

    private Integer id;
    private String content;
    private String userId;
    private Date registerDate;

}
