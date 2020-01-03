package com.ch.community.mapper;

import com.ch.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Date 2020/1/1 0001 13:55
 * @Created by Administrator
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER (NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED) VALUES (#{name},#{accountId},#{token},#{gmtCreat},#{gmtModified})")
    void insert(User user);

    @Select("SELECT * FROM USER WHERE TOKEN = #{token}")
    User findUserByToken(String token);

    @Select("SELECT * FROM USER WHERE id = #{id}")
    User findUserById(String id);
}
