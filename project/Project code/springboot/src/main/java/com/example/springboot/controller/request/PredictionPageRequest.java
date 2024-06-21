package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class PredictionPageRequest extends BaseRequest{
    private String username;
    private String phone;
    private String email;
}
