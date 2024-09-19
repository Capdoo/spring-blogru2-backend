package com.rafael.app.blogru.modules.sections;

import com.rafael.app.blogru.modules.headers.HeaderDto;
import com.rafael.app.blogru.modules.paragraphs.ParagraphDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SectionDto {
    private String id;
    private String title;
    private String target;
    private HeaderDto headerDto;
    private List<ParagraphDto> listParagraphsDto;
}
