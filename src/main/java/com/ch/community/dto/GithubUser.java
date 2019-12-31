package com.ch.community.dto;

/**
 * @ClassName GithubUser
 * @Description TODO
 * @Date 2019/12/31 0031 14:10
 * @Created by Administrator
 */
public class GithubUser {
    private Long id;
    private String name;
    private String  bio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
