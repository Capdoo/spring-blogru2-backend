package com.rafael.app.blogru.modules.topics;

import com.rafael.app.blogru.modules.subtopics.SubtopicDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class TopicDto {
    private String id;
    private String name;
    private String description;
    private String registerDate;
    private List<SubtopicDto> listSubtopicsDto;
}
