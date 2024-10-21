package com.rafael.app.blogru.modules.topics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor @AllArgsConstructor
public class Topic {

    @Id
    private String id;
    private String name;
    private String image;
    private Date registerDate;
    private String userId;
    private Date modificationDate;

    public Topic(String name, String image){
        this.name = name;
        this.image = image;
        this.registerDate = new Date();
    }

}
