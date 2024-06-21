package com.example.springboot.controller.request;


import lombok.Data;


@Data

public class UserLoginRequest {
    private String name;
    private String username;
    private String password;
}
