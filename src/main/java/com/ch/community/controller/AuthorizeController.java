package com.ch.community.controller;

import com.ch.community.dto.AccessTokenDTO;
import com.ch.community.dto.GithubUser;
import com.ch.community.mapper.UserMapper;
import com.ch.community.model.User;
import com.ch.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @ClassName AuthorizeController
 * @Description 登录权限处理
 * @Date 2019/12/31 0031 13:24
 * @Created by Administrator
 */

@Controller
public class AuthorizeController {

    @Value("${github.client.id}")
    private String ClientId;
    @Value("${github.client.secret}")
    private String ClientSecret;
    @Value("${github.redirect.url}")
    private String RedirectUri;
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;
    /**
     * 调用github登录回调
     * @return
     */
    @GetMapping("/callBack")
    public String callBack(@RequestParam(name="code") String code, @RequestParam(name="state") String state, HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(ClientId);
        accessTokenDTO.setClient_secret(ClientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(RedirectUri);
        accessTokenDTO.setState(state);
        String token = githubProvider.GetAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(token);
        if (githubUser != null) {
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setGmtCreat(System.currentTimeMillis());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtModified(user.getGmtCreat());
            userMapper.insert(user);
            request.getSession().setAttribute("user",githubUser);
        }
        return "redirect:/";
    }
}
