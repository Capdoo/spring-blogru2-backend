package com.rafael.app.blogru.modules.posts;

import com.rafael.app.blogru.modules.comments.Comments;
import com.rafael.app.blogru.modules.sections.Section;
import com.rafael.app.blogru.modules.subtopics.Subtopic;
import com.rafael.app.blogru.modules.topics.Topic;
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
    @DocumentReference
    private Comments comments;
    private Date registerDate;
    //contentv2
    @DocumentReference
    private List<Section> listSections;
    //Content
//    private List<HeaderModel> headers;
//    private List<ParagraphModel> paragraphs;
}