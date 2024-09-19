package com.rafael.app.blogru.modules.headers;

import com.rafael.app.blogru.modules.headers.Header;
import com.rafael.app.blogru.modules.headers.HeaderDto;

public class HeaderMapper {

    public static HeaderDto mapHeaderDto(Header header) {
        return HeaderDto.builder()
                .id(header.getId())
                .title(header.getTitle())
                .build();
    }
}
