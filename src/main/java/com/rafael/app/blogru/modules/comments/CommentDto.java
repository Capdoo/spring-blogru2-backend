package com.rafael.app.blogru.modules.comments;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommentDto {

    private String id;
    private String postId;
    private String commentParentId;
    private String userId;
    private String content;
    private String registerDate;
    private List<CommentDto> listCommentsDto;

}
