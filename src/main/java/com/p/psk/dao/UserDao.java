package com.p.psk.dao;

import com.p.psk.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") int id);


}
