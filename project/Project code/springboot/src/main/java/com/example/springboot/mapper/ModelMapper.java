package com.example.springboot.mapper;

import com.example.springboot.controller.DeepLearningModelPredict.request.ModelRequest;
import com.example.springboot.entity.Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModelMapper {
    List<Model> listByCondition(ModelRequest modelRequest);
}
