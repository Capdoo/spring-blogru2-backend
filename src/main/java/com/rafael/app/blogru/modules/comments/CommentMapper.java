package com.rafael.app.blogru.modules.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    public static CommentDto mapCommentDto(Comment comment) {
        List<CommentDto> listCommentsDto;

        listCommentsDto = new ArrayList<>();

        if (comment.getListComments() != null && !comment.getListComments().isEmpty()) {
            listCommentsDto = comment.getListComments().stream()
                    .map(CommentMapper::mapCommentDto)
                    .collect(Collectors.toList());
        }

        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .listCommentsDto(listCommentsDto)
                .registerDate(comment.getRegisterDate() == null ? "" : comment.getRegisterDate().toString())
                .build();
    }
}
