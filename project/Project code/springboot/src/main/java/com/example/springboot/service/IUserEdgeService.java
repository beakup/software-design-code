package com.example.springboot.service;

import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.dto.UserLoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.controller.request.UserLoginRequest;
import com.example.springboot.entity.UserEdge;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserEdgeService {

    List<UserEdge> list(); //查询所有

    PageInfo<UserEdge> page(BaseRequest baseRequest);

    void save(UserEdge obj);

    UserEdge getById(Integer id);

    void update(UserEdge obj);

    void deleteById(Integer id);

    UserLoginDTO login(UserLoginRequest request);

    void changePass(PasswordRequest request);

    UserEdge getByUserId(Integer id);

}
