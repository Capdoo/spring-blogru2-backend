package com.rafael.app.blogru.modules.subtopics.dto;

import lombok.*;

import java.util.Date;

@Getter @Setter
@Builder
public class SubtopicDTO {

    private String id;
    private String name;
    private String description;
    private Date registerDate;

}
