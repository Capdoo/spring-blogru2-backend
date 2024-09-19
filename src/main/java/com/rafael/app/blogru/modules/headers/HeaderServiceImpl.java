package com.rafael.app.blogru.modules.headers;

import com.rafael.app.blogru.modules.headers.Header;
import com.rafael.app.blogru.modules.headers.HeaderDto;
import com.rafael.app.blogru.modules.headers.HeaderRepository;
import com.rafael.app.blogru.modules.headers.HeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeaderServiceImpl implements HeaderService {

    @Autowired
    HeaderRepository headerRepository;

    @Override
    public Header createHeader(HeaderDto headerDto) {
        Header headerCreate;
        headerCreate = Header.builder()
                .title(headerDto.getTitle())//aqui pueden ir temas de estilos
                .build();
        return headerRepository.save(headerCreate);
    }

    @Override
    public Header readHeader(String id) {
        return headerRepository.findById(id).orElse(null);
    }

    @Override
    public Header updateHeader(HeaderDto headerDto) {
        Header headerUpdate;

        headerUpdate = readHeader(headerDto.getId());
        if (headerUpdate == null) {
            return null;
        }
        headerUpdate.setTitle(headerDto.getTitle());
        return headerRepository.save(headerUpdate);
    }

    @Override
    public Header deleteHeader(String id) {
        return null;
    }
}
