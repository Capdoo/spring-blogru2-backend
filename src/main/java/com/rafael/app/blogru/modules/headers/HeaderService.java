package com.rafael.app.blogru.modules.headers;

import com.rafael.app.blogru.modules.headers.Header;
import com.rafael.app.blogru.modules.headers.HeaderDto;

public interface HeaderService {
    Header createHeader(HeaderDto headerDto);
    Header readHeader(String id);
    Header updateHeader(HeaderDto headerDto);
    Header deleteHeader(String id);
}