package com.rafael.app.blogru.modules.headers.service;

import com.rafael.app.blogru.modules.headers.document.Header;
import com.rafael.app.blogru.modules.headers.dto.HeaderDto;
import com.rafael.app.blogru.modules.headers.repository.HeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeaderServiceImpl implements HeaderService{

    @Autowired
    HeaderRepository headerRepository;

    @Override
    public Header createHeader(HeaderDto headerDto) {
        Header headerCreate = Header.builder()
                .title(headerDto.getTitle())//aqui pueden ir temas de estilos
                .build();
        return headerRepository.save(headerCreate);
    }

    @Override
    public Header readHeader(String id) {
        return null;
    }

    @Override
    public Header updateHeader(HeaderDto headerDto) {
        return null;
    }

    @Override
    public Header deleteHeader(String id) {
        return null;
    }
}
