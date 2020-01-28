package com.ch.community.dto;

import com.ch.community.model.User;
import lombok.Data;

/**
 * @ClassName QuestionMapperDTO
 * @Description TODO
 * @Date 2020/1/3 0003 14:40
 * @Created by Administrator
 */
@Data
public class QuestionMapperDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private String creator;
    private String tag;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String name;
    private String accountId;
    private String token;
    private String avatarUrl;
}
