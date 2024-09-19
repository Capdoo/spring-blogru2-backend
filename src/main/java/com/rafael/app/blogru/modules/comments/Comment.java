package com.rafael.app.blogru.modules.comments;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

@Document
@Data
@Builder
public class Comment {
    @Id
    private String id;
//    private List<CommentModel> commentsList;
    private String content;
//    @DocumentReference
//    private String parentId;
    private List<Comment> listComments;
    private Date registerDate;
    private String userId;
    private Date modificationDate;
}
