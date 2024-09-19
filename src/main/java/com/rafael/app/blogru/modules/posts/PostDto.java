package com.rafael.app.blogru.modules.posts;

import com.rafael.app.blogru.modules.sections.SectionDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostDto {

    private String id;
    private String title;
    private String summary;

    private String topicId;
    private String subtopicId;

    private String userId;
    private String register_date;

    private List<SectionDto> listSectionsDto;
}
