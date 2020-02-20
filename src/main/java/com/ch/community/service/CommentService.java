package com.ch.community.service;

import com.ch.community.Exception.CustomizeErrorCode;
import com.ch.community.Exception.CustomizeException;
import com.ch.community.mapper.CommentMapper;
import com.ch.community.mapper.QuestionMapper;
import com.ch.community.model.Comment;
import com.ch.community.model.Question;
import enums.CommentTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
  @Autowired private CommentMapper commentMapper;
  @Autowired private QuestionMapper questionMapper;

  public void insert(Comment comment) {
    if (comment.getParentId() == null || comment.getParentId() == 0) {
      throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
    }
    if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
      throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
    }
    if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
      // 回复评论
      Comment parentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
      if (parentComment == null) {
        throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
      }
      commentMapper.insert(comment);
    } else if (comment.getType() == CommentTypeEnum.QUESTION.getType()){
      // 回复问题
      Question parentQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
      if (parentQuestion == null) {
        throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
      }
      commentMapper.insert(comment);
    }
  }
}
