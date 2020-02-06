package com.ch.community.controller;

import com.ch.community.Service.QuestionService;
import com.ch.community.dto.QuestionMapperDTO;
import com.ch.community.mapper.UserMapper;
import com.ch.community.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

  @Autowired private QuestionService questionService;

  @GetMapping("/profile/{action}")
  public String profile(
      @PathVariable(name = "action") String action,
      Model model,
      HttpServletRequest request,
      @RequestParam(required = false, defaultValue = "1") int page,
      @RequestParam(required = false, defaultValue = "5") int rows) {
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      return "redirect:/";
    }
    if ("question".equals(action)) {
      model.addAttribute("selection", "question");
      model.addAttribute("selectionName", "我的提问");
    } else if ("replies".equals(action)) {
      model.addAttribute("selection", "replies");
      model.addAttribute("selectionName", "我的回复");
    }

    PageHelper.startPage(page, rows);
    List<QuestionMapperDTO> questionList = questionService.findQuestionListById(user.getId());
    PageInfo<QuestionMapperDTO> pageInfo = new PageInfo<>(questionList);

    model.addAttribute("questionList", questionList);
    model.addAttribute("pageInfo", pageInfo);
    return "profile";
  }
}
