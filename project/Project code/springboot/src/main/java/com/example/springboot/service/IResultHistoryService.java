package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.ResultHistory;
import com.github.pagehelper.PageInfo;

public interface IResultHistoryService{

    void save(ResultHistory resultHistory);

    void deleteById(Integer id);

    ResultHistory getById(Integer id);

    PageInfo<ResultHistory> page(BaseRequest baseRequest);
}
