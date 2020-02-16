package com.ch.community.controller;

import com.ch.community.Service.UserService;
import com.ch.community.dto.AccessTokenDTO;
import com.ch.community.dto.GithubUser;
import com.ch.community.mapper.UserMapper;
import com.ch.community.model.User;
import com.ch.community.model.UserExample;
import com.ch.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName AuthorizeController @Description 登录权限处理 @Date 2019/12/31 0031 13:24 @Created by
 * Administrator
 */
@Controller
public class AuthorizeController {

  @Value("${github.client.id}")
  private String ClientId;

  @Value("${github.client.secret}")
  private String ClientSecret;

  @Value("${github.redirect.url}")
  private String RedirectUrl;

  @Autowired private GithubProvider githubProvider;

  @Autowired private UserMapper userMapper;

  @Autowired private UserService userService;

  /**
   * 调用github登录回调
   *
   * @return
   */
  @GetMapping("/callBack")
  public String callBack(
      @RequestParam(name = "code") String code,
      @RequestParam(name = "state") String state,
      HttpServletResponse response) {
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setClientId(ClientId);
    accessTokenDTO.setClientSecret(ClientSecret);
    accessTokenDTO.setCode(code);
    accessTokenDTO.setRedirectUrl(RedirectUrl);
    accessTokenDTO.setState(state);
    String githubToken = githubProvider.GetAccessToken(accessTokenDTO);
    GithubUser githubUser = githubProvider.getUser(githubToken);
    if (githubUser != null) {
      User user = new User();
      String token = UUID.randomUUID().toString();
      user.setToken(token);
      user.setName(githubUser.getName());
      user.setAccountId(String.valueOf(githubUser.getId()));
      user.setAvatarUrl(githubUser.getAvatarUrl());
      userService.createOrUpdate(user);
      response.addCookie(new Cookie("token", token));
    }
    return "redirect:/";
  }

  @GetMapping("logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    request.getSession().removeAttribute("user");
    response.addCookie(new Cookie("token", null));
    return "redirect:/";
  }
}
