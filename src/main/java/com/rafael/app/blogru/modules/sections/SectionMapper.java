package com.rafael.app.blogru.modules.sections;

import com.rafael.app.blogru.modules.headers.HeaderMapper;
import com.rafael.app.blogru.modules.paragraphs.ParagraphDto;
import com.rafael.app.blogru.modules.paragraphs.ParagraphMapper;
import com.rafael.app.blogru.modules.sections.Section;
import com.rafael.app.blogru.modules.sections.SectionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SectionMapper {



    public static SectionDto mapSectionDto(Section section){
        List<ParagraphDto> listParagraphsDto = new ArrayList<>();
        listParagraphsDto = section.getListParagraphs().stream()
                .map(ParagraphMapper::mapParagraphDto)
                .collect(Collectors.toList());

        return SectionDto.builder()
                .id(section.getId())
                .title(section.getTitle())
//                .target(section.getTarget())
//                .headerDto(HeaderMapper.mapHeaderDto(section.getHeader()))
                .listParagraphsDto(listParagraphsDto)
                .build();
    }
}
