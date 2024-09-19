package com.rafael.app.blogru.modules.sections;

import com.rafael.app.blogru.modules.headers.Header;
import com.rafael.app.blogru.modules.paragraphs.Paragraph;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

@Document
@Data
@Builder
public class Section {
    @Id
    private String id;
    private String title;
    private String target;
    @DocumentReference
    private Header header;
    @DocumentReference
    private List<Paragraph> listParagraphs;
    private Date registerDate;
    private String userId;
    private Date modificationDate;
}
