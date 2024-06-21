package com.example.springboot.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data//密码在登录接口返回不安全
public class UserLoginDTO {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String workplace;
    private String address;
    private String token;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String createtime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String logintime;
}
