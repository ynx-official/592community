package com.method.mapper;
/*
 * @author Yu
 * @date 2019/12/8 17:30
 *
 */

import com.method.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,head_pic,username,password,gmt_create,gmt_modify,token) values(#{accountId},#{name},#{headPic},#{username},#{password},#{gmtCreate},#{gmtModify},#{token})")
    void insert(User user);
}
