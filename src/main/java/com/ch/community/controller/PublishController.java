package com.ch.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName PublishController
 * @Description TODO
 * @Date 2020/1/1 0001 22:32
 * @Created by Administrator
 */
@Controller
public class PublishController
{
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }
}
