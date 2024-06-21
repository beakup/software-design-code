package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Knowledge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//用来操作数据库
@Mapper
public interface KnowledgeMapper {
    
    List<Knowledge> list(); //查询所有

    List<Knowledge> listByCondition(BaseRequest baseRequest); //多态


    void save(Knowledge obj);

    Knowledge getById(Integer id);

    void updateById(Knowledge user);

    void deleteById(Integer id);


    Knowledge getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int updatePassword(PasswordRequest request);
}

