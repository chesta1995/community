package com.ch.community.service;

import com.ch.community.Exception.CustomizeErrorCode;
import com.ch.community.Exception.CustomizeException;
import com.ch.community.dto.QuestionMapperDTO;
import com.ch.community.mapper.QuestionMapper;
import com.ch.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName QuestionService @Description TODO @Date 2020/1/3 0003 14:39 @Created by Administrator
 */
@Service
public class QuestionService {
  @Autowired private QuestionMapper questionMapper;
  @Autowired private UserMapper userMapper;

  public List<QuestionMapperDTO> findQuestionList() {
    List<QuestionMapperDTO> questionList = questionMapper.findQuestionList();
    return questionList;
  }

  public List<QuestionMapperDTO> findQuestionListById(Integer userId) {
    List<QuestionMapperDTO> questionList = questionMapper.findQuestionListById(userId);
    return questionList;
  }

  public QuestionMapperDTO getQuestById(Integer id) {
    QuestionMapperDTO questInfo = questionMapper.getQuestById(id);
    if (questInfo.getId() == null) {
      throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
    }
    return questInfo;
  }
}
