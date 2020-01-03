package com.ch.community.model;

import lombok.Data;

/**
 * @ClassName Question
 * @Description TODO
 * @Date 2020/1/2 0002 14:16
 * @Created by Administrator
 */
@Data
public class Question {
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
}