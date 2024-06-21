package com.example.springboot.controller.DeepLearningModelPredict.request;

import com.example.springboot.controller.request.BaseRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ResultHistoryPageRequst  extends BaseRequest {
    private Integer userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String end;
    private String orderField;
    private String orderField1;
    private String orderField2;
    private String orderType;
    private String inform;
}
