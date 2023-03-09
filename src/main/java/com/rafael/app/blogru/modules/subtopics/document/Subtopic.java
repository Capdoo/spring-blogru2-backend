package com.rafael.app.blogru.modules.subtopics.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor @AllArgsConstructor
public class Subtopic {

    @Id
    private String id;
    private String name;
    private String description;
    private Date registerDate;

    public Subtopic(String name, String description){
        this.name = name;
        this.description = description;
        this.registerDate = new Date();
    }

}
