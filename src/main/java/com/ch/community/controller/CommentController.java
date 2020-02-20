package com.ch.community.controller;

import com.ch.community.Exception.CustomizeErrorCode;
import com.ch.community.service.CommentService;
import com.ch.community.dto.CommentForm;
import com.ch.community.dto.ResultDTO;
import com.ch.community.model.Comment;
import com.ch.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class CommentController {

  @Autowired private CommentService commentService;

  @ResponseBody
  @RequestMapping(value = "/comment", method = RequestMethod.POST)
  public Object post(@RequestBody CommentForm commentForm, HttpServletRequest request){
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
        return ResultDTO.errorOf(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
    }

    Comment comment = new Comment();
    comment.setComment(commentForm.getComment());
    comment.setParentId(commentForm.getParentId());
    comment.setType(commentForm.getType());
    comment.setGmtCreate(System.currentTimeMillis());
    comment.setGmtModified(System.currentTimeMillis());
    comment.setAccountId(user.getId());
    commentService.insert(comment);

    return ResultDTO.okof();
  }
}
