package com.example.springboot.entity;

import lombok.Data;


@Data
public class Patient {
    private Integer id;
    private String name;
    private Integer age;
    private boolean sex;
    private String inform;
    private Integer fileId;

}
