package com.ch.community.controller;

import com.ch.community.dto.QuestionMapperDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ch.community.Service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/** @ClassName Hello @Description TODO @Date 2019/12/30 0030 14:56 @Created by Administrator */
@Controller
public class IndexController {

  @Autowired private QuestionService questionService;

  /**
   *首页
   * @param model
   * @param page
   * @param rows
   * @return
   */
  @GetMapping("/")
  public String index(
      Model model,
      @RequestParam(required = false, defaultValue = "1") int page,
      @RequestParam(required = false, defaultValue = "5") int rows) {

    PageHelper.startPage(page, rows);
    List<QuestionMapperDTO> questionList = questionService.findQuestionList();
    PageInfo<QuestionMapperDTO> pageInfo = new PageInfo<>(questionList);

    model.addAttribute("questionList", questionList);
    model.addAttribute("pageInfo", pageInfo);
    return "index";
  }
}
