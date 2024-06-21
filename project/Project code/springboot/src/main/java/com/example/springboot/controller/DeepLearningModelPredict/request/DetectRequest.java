package com.example.springboot.controller.DeepLearningModelPredict.request;

import lombok.Data;

@Data
public class DetectRequest {
    private Integer userId;
    private Integer patientId;
    private String modelName;
    private String dataSet;
    private String task;
    private String inputUrl;
}
