package com.ch.community.dto;

import lombok.Data;

/**
 * @ClassName AccessTokenDTO
 * @Description TODO
 * @Date 2019/12/31 0031 13:36
 * @Created by Administrator
 */
@Data
public class AccessTokenDTO {
    private String clientId;
    private String redirectUrl;
    private String code;
    private String clientSecret;
    private String state;
}
