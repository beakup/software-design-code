package com.example.springboot.service.impl;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.ResultHistory;
import com.example.springboot.mapper.ResultHistoryMapper;
import com.example.springboot.service.IResultHistoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultHistoryService implements IResultHistoryService {
    @Autowired
    ResultHistoryMapper resultHistoryMapper;

    @Override
    public void save(ResultHistory resultHistory){
        resultHistoryMapper.save(resultHistory);
    }

    @Override
    public void deleteById(Integer id) {
        resultHistoryMapper.deleteById(id);
    }

    @Override
    public ResultHistory getById(Integer id) {

        return resultHistoryMapper.getById(id);
    }

    @Override
    public PageInfo<ResultHistory> page(BaseRequest baseRequest){
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<ResultHistory>  resultHistories = resultHistoryMapper.listByCondition(baseRequest);
        return new PageInfo<>(resultHistories);
    }


}
