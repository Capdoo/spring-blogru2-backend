package com.rafael.app.blogru.modules.comments;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDTO {

    private String id;
    private String post_id;
    private String user_id;
    private String content;
    private String register_date;

}
