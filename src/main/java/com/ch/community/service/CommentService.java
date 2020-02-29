package com.ch.community.service;

import com.ch.community.Exception.CustomizeErrorCode;
import com.ch.community.Exception.CustomizeException;
import com.ch.community.dto.CommentDTO;
import com.ch.community.mapper.CommentMapper;
import com.ch.community.mapper.QuestionMapper;
import com.ch.community.mapper.UserMapper;
import com.ch.community.model.*;
import com.ch.community.enums.CommentTypeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
  @Autowired private CommentMapper commentMapper;
  @Autowired private QuestionMapper questionMapper;
  @Autowired private UserMapper userMapper;

  @Transactional
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
    } else if (comment.getType() == CommentTypeEnum.QUESTION.getType()) {
      // 回复问题
      Question parentQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
      if (parentQuestion == null) {
        throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
      }
      commentMapper.insert(comment);
    }
  }

  public List<CommentDTO> ListByQuestionId(Integer id) {
    CommentExample example = new CommentExample();
    example
        .createCriteria()
        .andParentIdEqualTo(id)
        .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
    List<Comment> comments = commentMapper.selectByExample(example);

    if (comments.size() == 0) {
      return new ArrayList<>();
    }
    Set<Integer> commentors =
        comments.stream().map(comment -> comment.getAccountId()).collect(Collectors.toSet());
    ArrayList<Integer> userIds = new ArrayList<>();
    userIds.addAll(commentors);

    UserExample userExample = new UserExample();
    userExample.createCriteria()
            .andIdIn(userIds);
    List<User> users = userMapper.selectByExample(userExample);
    Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

    List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
      CommentDTO commentDTO = new CommentDTO();
      BeanUtils.copyProperties(comment, commentDTO);
      commentDTO.setUser(userMap.get(commentDTO.getAccountId()));
      return commentDTO;
    }).collect(Collectors.toList());
    return commentDTOS;
  }
}
