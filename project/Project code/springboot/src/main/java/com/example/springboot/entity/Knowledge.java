package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data //编译后帮助生成模版代码
//这里的User属性是和MySQL对应上的
public class Knowledge {
    private Integer id;
    private String username;
    private String phone;
    private String email;
    private String password;
    private String path;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updatetime;
    private boolean status;

}
