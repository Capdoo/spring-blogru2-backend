package com.rafael.app.blogru.modules.subtopics;

import com.rafael.app.blogru.modules.topics.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subtopic {

    @Id
    private String id;
    private String name;
    private String image;
    private Date registerDate;
    private String userId;
    private Date modificationDate;

    @DocumentReference
    private Topic topic;

    public Subtopic(String name, String image){
        this.name = name;
        this.image = image;
        this.registerDate = new Date();
    }

}
