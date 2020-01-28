package com.ch.community.mapper;

import com.ch.community.dto.QuestionMapperDTO;
import com.ch.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName QuestionMapper
 * @Description TODO
 * @Date 2020/1/2 0002 14:06
 * @Created by Administrator
 */
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO QUESTION(TITLE, DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, TAG) VALUES (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void create(Question question);

   @Select("SELECT * FROM QUESTION Q LEFT JOIN USER U ON Q.CREATOR = U.ID")
    List<QuestionMapperDTO> findQuestionList();
}