package com.rafael.app.blogru.modules.paragraphs;

import com.rafael.app.blogru.modules.paragraphs.Paragraph;
import com.rafael.app.blogru.modules.paragraphs.ParagraphDto;

public class ParagraphMapper {

    public static ParagraphDto mapParagraphDto(Paragraph paragraph){

        return ParagraphDto.builder()
                .id(paragraph.getId())
                .content(paragraph.getContent())
                .title(paragraph.getTitle())
                .description(paragraph.getDescription())
                .build();
    }
}
