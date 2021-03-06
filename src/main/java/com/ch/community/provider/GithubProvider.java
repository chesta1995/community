package com.ch.community.provider;

import com.alibaba.fastjson.JSON;
import com.ch.community.dto.AccessTokenDTO;
import com.ch.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName GithubProvider
 * @Description TODO
 * @Date 2019/12/31 0031 13:34
 * @Created by Administrator
 */
@Component
public class GithubProvider {
    public String GetAccessToken(AccessTokenDTO accessTokenDTO) {
  //      MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

 //       RequestBody body = RequestBody.create( JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token?client_id=" + accessTokenDTO.getClientId() +"&client_secret="
                + accessTokenDTO.getClientSecret() + "&code=" + accessTokenDTO.getCode())
 //               .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
        }
        return null;
    }

    public GithubUser getUser(String AccessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + AccessToken)
                .build();
        try (
            Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            GithubUser githubUser = JSON.parseObject(result, GithubUser.class);
            return githubUser;
        }catch(IOException e){

        }
        return null;
    }
}
