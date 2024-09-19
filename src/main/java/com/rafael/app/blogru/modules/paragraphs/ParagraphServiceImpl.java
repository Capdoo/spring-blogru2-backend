package com.rafael.app.blogru.modules.paragraphs;

import com.rafael.app.blogru.modules.paragraphs.Paragraph;
import com.rafael.app.blogru.modules.paragraphs.ParagraphDto;
import com.rafael.app.blogru.modules.paragraphs.ParagraphRepository;
import com.rafael.app.blogru.modules.paragraphs.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParagraphServiceImpl implements ParagraphService {

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
        return paragraphRepository.findById(id).orElse(null);
    }

    @Override
    public Paragraph updateParagraph(ParagraphDto paragraphDto) {
        //avanzar esto
        Paragraph paragraphUpdate;

        paragraphUpdate = readParagraph(paragraphDto.getId());
        if (paragraphUpdate == null) {
            return null;
        }

        paragraphUpdate = readParagraph(paragraphDto.getId());
        paragraphUpdate.setTitle(paragraphDto.getTitle());
        paragraphUpdate.setDescription(paragraphDto.getDescription());
        paragraphUpdate.setContent(paragraphDto.getContent());

        return paragraphRepository.save(paragraphUpdate);
    }

    @Override
    public Paragraph deleteParagraph(ParagraphDto paragraphDto) {
        return null;
    }

    @Override
    public void deleteAllParagraphs(List<String> listIds) {
        List<Paragraph> listParagraphsDelete;
        listParagraphsDelete = listIds.stream()
                .map(this::readParagraph)
                .collect(Collectors.toList());
        paragraphRepository.deleteAll(listParagraphsDelete);
    }
}