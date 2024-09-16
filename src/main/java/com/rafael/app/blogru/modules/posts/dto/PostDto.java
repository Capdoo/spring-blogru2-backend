package com.rafael.app.blogru.modules.posts.dto;

import com.rafael.app.blogru.modules.posts.models.HeaderModel;
import com.rafael.app.blogru.modules.posts.models.ParagraphModel;
import com.rafael.app.blogru.modules.sections.dto.SectionDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostDto {

    private String id;
    private String title;
    private String summary;

    private String topic_id;

    private String subtopic_id;
    private String user_id;
    private String register_date;

    private List<SectionDto> listSectionsDto;

    //Content
//    private List<HeaderModel> headers;
//    private List<ParagraphModel> paragraphs;

}
