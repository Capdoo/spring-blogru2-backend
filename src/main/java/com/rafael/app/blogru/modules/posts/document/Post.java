package com.rafael.app.blogru.modules.posts.document;

import com.rafael.app.blogru.modules.posts.models.CommentModel;
import com.rafael.app.blogru.modules.posts.models.HeaderModel;
import com.rafael.app.blogru.modules.posts.models.ParagraphModel;
import com.rafael.app.blogru.modules.subtopics.document.Subtopic;
import com.rafael.app.blogru.modules.topics.document.Topic;
import com.rafael.app.blogru.security.document.User;
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
public class Post {

    @Id
    private String id;
    private String title;
    private String summary;
    @DocumentReference
    private Topic topic;
    @DocumentReference
    private Subtopic subtopic;
    @DocumentReference
    private User user;
    private Date registerDate;

    //Content
    private List<HeaderModel> headers;
    private List<ParagraphModel> paragraphs;
    private List<CommentModel> comments;

}
