package com.example.springboot.service;

import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Knowledge;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IKnowledgeService {

    List<Knowledge> list(); //查询所有

    PageInfo<Knowledge> page(BaseRequest baseRequest);

    void save(Knowledge obj);

    Knowledge getById(Integer id);

    void update(Knowledge obj);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest request);

    void changePass(PasswordRequest request);

}
