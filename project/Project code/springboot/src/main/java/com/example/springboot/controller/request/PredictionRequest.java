package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class PredictionRequest extends BaseRequest{
    private Integer id;
    private String modelname;
    private String model;
}
