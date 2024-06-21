package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@JsonFormat
@Data
public class ResultHistory {
    private Integer id;

    private Integer patientId;

    private Integer userId;

    private String modelName;

    private String dataSet;

    private String inputUrl;

    private String task;

    private String inform;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private String createtime;
}
