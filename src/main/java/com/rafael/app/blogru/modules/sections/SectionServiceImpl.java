package com.rafael.app.blogru.modules.sections;

import com.rafael.app.blogru.modules.headers.Header;
import com.rafael.app.blogru.modules.headers.HeaderService;
import com.rafael.app.blogru.modules.paragraphs.Paragraph;
import com.rafael.app.blogru.modules.paragraphs.ParagraphDto;
import com.rafael.app.blogru.modules.paragraphs.ParagraphService;
import com.rafael.app.blogru.modules.sections.Section;
import com.rafael.app.blogru.modules.sections.SectionDto;
import com.rafael.app.blogru.modules.sections.SectionRepository;
import com.rafael.app.blogru.modules.sections.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {

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
        Section sectionUpdate;
        Header header;
        List<Paragraph> listParagraphs;

        if (sectionDto.getHeaderDto().getId() == null) {//create header
            header = this.headerService.createHeader(sectionDto.getHeaderDto());
        } else {//update header
            header = this.headerService.updateHeader(sectionDto.getHeaderDto());
        }

        sectionUpdate = readSection(sectionDto.getId());
        if (sectionUpdate == null) {
            return null;
        }

        sectionUpdate.setTitle(sectionDto.getTitle());
//        sectionUpdate.setTarget(sectionDto.getTarget());
        sectionUpdate.setTarget("sample");
        sectionUpdate.setHeader(header);

        this.deleteParagraphs(sectionUpdate, sectionDto);

        List<Paragraph> listParagraphDb = new ArrayList<>();
        for (ParagraphDto paragraphDto: sectionDto.getListParagraphsDto()) {
            if (paragraphDto.getId()  == null) {
                //create paragraph
                listParagraphDb.add(this.paragraphService.createParagraph(paragraphDto));
            } else {
                //update paragraph
                listParagraphDb.add(this.paragraphService.updateParagraph(paragraphDto));
            }
        }

        sectionUpdate.setListParagraphs(listParagraphDb);
        return sectionRepository.save(sectionUpdate);
    }

    public void deleteParagraphs(Section section, SectionDto sectionDto) {
        List<String> listParagraphsIdDb;
        List<String> listParagraphsIdDto;
        List<String> listParagraphsIdDelete;

        //eliminacion
        listParagraphsIdDb = section.getListParagraphs().stream()
                .map(Paragraph::getId)
                .collect(Collectors.toList());

        listParagraphsIdDto = sectionDto.getListParagraphsDto()
                .stream()
                .map(ParagraphDto::getId)
                .collect(Collectors.toList());

        listParagraphsIdDelete = listParagraphsIdDb.stream()
                .filter(e -> !listParagraphsIdDto.contains(e))
                .collect(Collectors.toList());

        if (!listParagraphsIdDelete.isEmpty()) {
            this.paragraphService.deleteAllParagraphs(listParagraphsIdDelete);
        }

    }

    @Override
    public Section deleteSection(String id) {
        return null;
    }

    @Override
    public void deleteAllSections(List<String> listIds) {
        List<Section> listSectionsDelete;
        listSectionsDelete = listIds.stream()
                .map(this::readSection)
                .collect(Collectors.toList());
        sectionRepository.deleteAll(listSectionsDelete);
    }
}
