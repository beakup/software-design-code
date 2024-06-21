package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.ResultHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResultHistoryMapper {
    void save(ResultHistory obj);

    void deleteById(@Param("id") Integer id);

    ResultHistory getById(@Param("id") Integer id);

    List<ResultHistory> listByCondition(BaseRequest baseRequest);
}
