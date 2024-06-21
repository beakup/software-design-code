package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.UserEdge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//用来操作数据库
@Mapper
public interface UserEdgeMapper {

    //    @Select("select * from user")
    List<UserEdge> list(); //查询所有

    List<UserEdge> listByCondition(BaseRequest baseRequest);


    void save(UserEdge obj);

    UserEdge getById(Integer id);

    void updateById(UserEdge user);

    void deleteById(Integer id);


    UserEdge getBynameAndPassword(@Param("name") String name, @Param("password") String password);

    int updatePassword(PasswordRequest request);

    UserEdge getByname(String name);
    UserEdge getByUserId(Integer id);
}

