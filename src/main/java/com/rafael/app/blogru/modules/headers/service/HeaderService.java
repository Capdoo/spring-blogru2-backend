package com.rafael.app.blogru.modules.headers.service;

import com.rafael.app.blogru.modules.headers.document.Header;
import com.rafael.app.blogru.modules.headers.dto.HeaderDto;

public interface HeaderService {
    Header createHeader(HeaderDto headerDto);
    Header readHeader(String id);
    Header updateHeader(HeaderDto headerDto);
    Header deleteHeader(String id);
}