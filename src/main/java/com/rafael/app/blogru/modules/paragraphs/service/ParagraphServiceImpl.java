package com.rafael.app.blogru.modules.paragraphs.service;

import com.rafael.app.blogru.modules.paragraphs.document.Paragraph;
import com.rafael.app.blogru.modules.paragraphs.dto.ParagraphDto;
import com.rafael.app.blogru.modules.paragraphs.repository.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParagraphServiceImpl implements ParagraphService{

    @Autowired
    ParagraphRepository paragraphRepository;

    @Override
    public Paragraph createParagraph(ParagraphDto paragraphDto) {
        Paragraph paragraphCreate = Paragraph.builder()
                .title(paragraphDto.getTitle())
                .description(paragraphDto.getDescription())
                .content(paragraphDto.getContent())
                .build();

        return paragraphRepository.save(paragraphCreate);
    }

    @Override
    public Paragraph readParagraph(String id) {
        return null;
    }

    @Override
    public Paragraph updateParagraph(ParagraphDto paragraphDto) {
        return null;
    }

    @Override
    public Paragraph deleteParagraph(ParagraphDto paragraphDto) {
        return null;
    }
}
