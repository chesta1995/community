package com.ch.community.model;

import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Date 2020/1/1 0001 13:59
 * @Created by Administrator
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreat;
    private Long gmtModified;
    private String avatarUrl;
}
