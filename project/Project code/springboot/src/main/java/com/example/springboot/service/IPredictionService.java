package com.example.springboot.service;

import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Prediction;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IPredictionService {

    List<Prediction> list(); //查询所有

    PageInfo<Prediction> page(BaseRequest baseRequest);

    void save(Prediction obj);

    Prediction getById(Integer id);

    void update(Prediction obj);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest request);

    void changePass(PasswordRequest request);


}
