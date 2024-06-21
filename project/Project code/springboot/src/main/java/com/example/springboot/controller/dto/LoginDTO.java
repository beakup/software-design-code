package com.example.springboot.controller.dto;

import lombok.Data;

@Data//密码在登录接口返回不安全
public class LoginDTO {
    private Integer id;
    private String username;
    private String phone;
    private String email;
    private String token;
}
