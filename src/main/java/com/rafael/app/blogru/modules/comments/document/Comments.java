package com.rafael.app.blogru.modules.comments.document;

import com.rafael.app.blogru.modules.comments.models.CommentModel;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@Builder
public class Comments {
    @Id
    private String id;
    private List<CommentModel> commentsList;
    private Date registerDate;
}
