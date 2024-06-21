package com.example.springboot.service.impl;

import com.example.springboot.controller.DeepLearningModelPredict.request.ModelRequest;
import com.example.springboot.entity.Model;
import com.example.springboot.mapper.ModelMapper;
import com.example.springboot.service.IModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ModelService implements IModelService {
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Model> listByCondition(@RequestBody ModelRequest modelRequest) {
        return modelMapper.listByCondition(modelRequest);
    }
}
