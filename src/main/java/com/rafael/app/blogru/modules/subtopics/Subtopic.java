package com.rafael.app.blogru.modules.subtopics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String description;
    private Date registerDate;
    private String userId;
    private Date modificationDate;

    public Subtopic(String name, String description){
        this.name = name;
        this.description = description;
        this.registerDate = new Date();
    }

}
