package com.example.springboot.controller.DeepLearningModelPredict.request;

import lombok.Data;

@Data
public class DatasetRequest {
    private String name;
    private String task;
    private String inform;
}
