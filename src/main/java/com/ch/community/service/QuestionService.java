package com.ch.community.service;

import com.ch.community.Exception.CustomizeErrorCode;
import com.ch.community.Exception.CustomizeException;
import com.ch.community.dto.QuestionMapperDTO;
import com.ch.community.mapper.QuestionMapper;
import com.ch.community.model.Question;
import com.ch.community.model.QuestionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName QuestionService @Description TODO @Date 2020/1/3 0003 14:39 @Created by Administrator
 */
@Service
public class QuestionService {
    @Autowired private QuestionMapper questionMapper;

    /**
     *查询问题列表
     * @return
     */
    public List<QuestionMapperDTO> findQuestionList() {
        List<QuestionMapperDTO> questionList = questionMapper.findQuestionList();
        return questionList;
    }

    /**
     *根据用户查询问题
     * @param userId
     * @return
     */
    public List<QuestionMapperDTO> findQuestionListById(Integer userId) {
        List<QuestionMapperDTO> questionList = questionMapper.findQuestionListById(userId);
        return questionList;
    }

    /**
     * 根据ID查询问题
     * @param id
     * @return
     */
    public QuestionMapperDTO getQuestById(Integer id) {
        QuestionMapperDTO questInfo = questionMapper.getQuestById(id);
        if (questInfo.getId() == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        Question question = new Question();
        question.setViewCount(questInfo.getViewCount() + 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(id);
        questionMapper.updateByExampleSelective(question,questionExample);
        return questInfo;
    }
}