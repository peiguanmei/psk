package com.p.psk.dao;

import com.p.psk.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 加componen注解是因为在UserService中会警告can not autowired,但是不影响程序运行
 * 原因可能是没有使用xml配置文件
 * 或者在启动类中添加@ComponentScan(basePackages = {"com.p.psk.dao"})也可以解决
 */
@Mapper
@Component
public interface UserDao {

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    @Select("select * from sk_user where id = #{id}")
    public User getById(@Param("id") long id);


}
