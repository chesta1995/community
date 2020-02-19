package com.ch.community.dto;

import lombok.Data;

@Data
public class CommentForm {
    private Long parentId;
    private Integer type;
    private String comment;
}
