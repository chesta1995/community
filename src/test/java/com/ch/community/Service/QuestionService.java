package com.ch.community.Service;

import com.ch.community.Exception.CustomizeErrorCode;
import com.ch.community.Exception.CustomizeException;
import com.ch.community.dto.QuestionMapperDTO;
import com.ch.community.mapper.QuestionMapper;
import com.ch.community.mapper.UserMapper;
import com.ch.community.model.Question;
import com.ch.community.model.QuestionExample;
import com.ch.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName QuestionService @Description TODO @Date 2020/1/3 0003 14:39 @Created by Administrator
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

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
        Integer ViewCount = questInfo.getViewCount() + 1;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(id);
        Question question = new Question();
        question.setId(id);
        question.setViewCount(ViewCount);
        questionMapper.updateByExampleSelective(question, questionExample);
        return questInfo;
    }
}
