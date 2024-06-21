package com.example.springboot.service;

import com.example.springboot.controller.DeepLearningModelPredict.request.ModelRequest;
import com.example.springboot.entity.Model;

import java.util.List;

public interface IModelService {
    List<Model> listByCondition(ModelRequest modelRequest);
}
