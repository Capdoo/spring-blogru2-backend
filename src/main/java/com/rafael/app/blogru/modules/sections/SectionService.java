package com.rafael.app.blogru.modules.sections;

import com.rafael.app.blogru.modules.sections.Section;
import com.rafael.app.blogru.modules.sections.SectionDto;

import java.util.List;

public interface SectionService {

    Section createSection(SectionDto sectionDto);
    Section readSection(String id);
    Section updateSection(SectionDto sectionDto);
    Section deleteSection(String id);
    void deleteAllSections(List<String> listIds);
}
