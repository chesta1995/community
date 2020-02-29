package com.ch.community.controller;

import com.ch.community.dto.CommentDTO;
import com.ch.community.service.CommentService;
import com.ch.community.service.QuestionService;
import com.ch.community.dto.QuestionMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        QuestionMapperDTO questInfo = questionService.getQuestById(id);
        List<CommentDTO> comments = commentService.ListByQuestionId(id);
        model.addAttribute("question",questInfo);
        model.addAttribute("comments",comments);
        return "question";
    }
}
