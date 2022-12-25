package com.example.mysqllearningdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mysqllearningdemo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where name = #{name} and password = #{password}")
    User getUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Select("select * from user where email = #{email}")
    User getUserByEmail(@Param("email") String email);

    @Select("select email from user where id = #{userId}")
    String getEmailById(String userId);
}
