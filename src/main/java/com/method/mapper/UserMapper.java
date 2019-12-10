package com.method.mapper;
/*
 * @author Yu
 * @date 2019/12/8 17:30
 *
 */

import com.method.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,avatar_url,username,password,gmt_create,gmt_modify,token) values(#{accountId},#{name},#{avatarUrl},#{username},#{password},#{gmtCreate},#{gmtModify},#{token})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);

    @Select("select * from user where id = #{id}")
    User findByID(Long id);
}
