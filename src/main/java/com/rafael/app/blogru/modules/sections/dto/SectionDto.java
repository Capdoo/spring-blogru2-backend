package com.rafael.app.blogru.modules.sections.dto;

import com.rafael.app.blogru.modules.headers.dto.HeaderDto;
import com.rafael.app.blogru.modules.paragraphs.dto.ParagraphDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SectionDto {
    private String id;
    private String title;
    private String header;
    private String target;
    private HeaderDto headerDto;
    private List<ParagraphDto> listParagraphsDto;
}
