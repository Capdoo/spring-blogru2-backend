package com.rafael.app.blogru.modules.sections.service;

import com.rafael.app.blogru.modules.sections.document.Section;
import com.rafael.app.blogru.modules.sections.dto.SectionDto;

public interface SectionService {

    Section createSection(SectionDto sectionDto);
    Section readSection(String id);
    Section updateSection(SectionDto sectionDto);
    Section deleteSection(String id);
}
