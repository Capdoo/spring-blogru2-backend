package com.rafael.app.blogru.modules.topics.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor @AllArgsConstructor
public class Topic {

    private String id;
    private String name;
    private String description;
    private Date registerDate;

    public Topic(String name, String description){
        this.name = name;
        this.description = description;
        this.registerDate = new Date();
    }

}
