package com.ch.community.dto;

import com.ch.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private String comment;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer accountId;
    private Integer agreeCount;
    private User user;
}
