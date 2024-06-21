package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@JsonFormat
@Data
public class Dataset {
    private String name;
    private String inform;
    private String filePath;
    private String task;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String createtime;

}
