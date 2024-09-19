package com.rafael.app.blogru.modules.paragraphs;

import com.rafael.app.blogru.modules.paragraphs.Paragraph;
import com.rafael.app.blogru.modules.paragraphs.ParagraphDto;

import java.util.List;

public interface ParagraphService {

    Paragraph createParagraph(ParagraphDto paragraphDto);
    Paragraph readParagraph(String id);
    Paragraph updateParagraph(ParagraphDto paragraphDto);
    Paragraph deleteParagraph(ParagraphDto paragraphDto);
    void deleteAllParagraphs(List<String> listIds);

}
