package com.rafael.app.blogru.modules.sections.service;

import com.rafael.app.blogru.modules.headers.document.Header;
import com.rafael.app.blogru.modules.headers.service.HeaderService;
import com.rafael.app.blogru.modules.paragraphs.document.Paragraph;
import com.rafael.app.blogru.modules.paragraphs.service.ParagraphService;
import com.rafael.app.blogru.modules.sections.document.Section;
import com.rafael.app.blogru.modules.sections.dto.SectionDto;
import com.rafael.app.blogru.modules.sections.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    ParagraphService paragraphService;
    @Autowired
    HeaderService headerService;

    @Override
    public Section createSection(SectionDto sectionDto) {
        Section sectionCreate;
        Header header;
        List<Paragraph> listParagraphs;

        header = headerService.createHeader(sectionDto.getHeaderDto());

        listParagraphs = sectionDto.getListParagraphsDto().stream()
                .map(this.paragraphService::createParagraph)
                .collect(Collectors.toList());

        sectionCreate = Section.builder()
                .title(sectionDto.getTitle())
                .target(sectionDto.getTarget())
                .header(header)
                .listParagraphs(listParagraphs)
                .build();

        return sectionRepository.save(sectionCreate);
    }

    @Override
    public Section readSection(String id) {
        return sectionRepository.findById(id).orElse(null);
    }

    @Override
    public Section updateSection(SectionDto sectionDto) {
        return null;
    }

    @Override
    public Section deleteSection(String id) {
        return null;
    }
}
