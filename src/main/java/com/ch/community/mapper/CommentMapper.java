package com.ch.community.mapper;

import com.ch.community.model.Comment;
import com.ch.community.model.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    long countByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    int deleteByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    int insert(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    int insertSelective(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    List<Comment> selectByExampleWithRowbounds(CommentExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    List<Comment> selectByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    Comment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Tue Feb 18 20:50:47 CST 2020
     */
    int updateByPrimaryKey(Comment record);
}