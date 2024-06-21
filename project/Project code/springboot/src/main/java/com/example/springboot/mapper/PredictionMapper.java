package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Prediction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//用来操作数据库
@Mapper
public interface PredictionMapper {
    
    List<Prediction> list(); //查询所有

    List<Prediction> listByCondition(BaseRequest baseRequest);


    void save(Prediction obj);


    Prediction getById(Integer id);

    void updateById(Prediction user);

    void deleteById(Integer id);


    Prediction getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int updatePassword(PasswordRequest request);
}

