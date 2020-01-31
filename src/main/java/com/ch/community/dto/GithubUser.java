package com.ch.community.dto;

import lombok.Data;

/**
 * @ClassName GithubUser
 * @Description TODO
 * @Date 2019/12/31 0031 14:10
 * @Created by Administrator
 */
@Data
public class GithubUser {
    private Long id;
    private String name;
    private String bio;
    private String avatarUrl;
}
