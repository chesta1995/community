package com.ch.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName Hello
 * @Description TODO
 * @Date 2019/12/30 0030 14:56
 * @Created by Administrator
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello (@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name",name + "2");
        return "hello";
    }

    @GetMapping("/index")
    public String index () {
        return "index";
    }
}
