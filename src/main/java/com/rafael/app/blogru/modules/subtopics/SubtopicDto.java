package com.rafael.app.blogru.modules.subtopics;

import lombok.*;

import java.util.Date;

@Getter @Setter
@Builder
public class SubtopicDto {

    private String id;
    private String name;
    private String description;
    private Date registerDate;
    private String image;

}
