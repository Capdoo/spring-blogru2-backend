package com.rafael.app.blogru.modules.paragraphs.service;

import com.rafael.app.blogru.modules.paragraphs.document.Paragraph;
import com.rafael.app.blogru.modules.paragraphs.dto.ParagraphDto;

public interface ParagraphService {

    Paragraph createParagraph(ParagraphDto paragraphDto);
    Paragraph readParagraph(String id);
    Paragraph updateParagraph(ParagraphDto paragraphDto);
    Paragraph deleteParagraph(ParagraphDto paragraphDto);

}
