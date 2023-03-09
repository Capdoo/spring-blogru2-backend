package com.rafael.app.blogru.security.document;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class Role {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
