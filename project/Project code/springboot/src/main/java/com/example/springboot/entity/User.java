package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class User {
    private String id;
    private String pickname;
    private String password;
    private String email;
    private String occupation;
    private String workplace;
    private String city;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String logintime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String createtime;
}
