package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data //编译后帮助生成模版代码
//这里的User属性是和MySQL对应上的
public class UserEdge {
    private Integer id;
    private String name;
    private String username;
    private Integer age;
    private String sex;
    private String phone;
    private String address;
    private String password;
    private String cover;
    private boolean status;

    private String email;
    private String occupation;
    private String workplace;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updatetime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date logintime;

}
