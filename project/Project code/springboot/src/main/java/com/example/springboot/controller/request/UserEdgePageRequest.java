package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class UserEdgePageRequest extends BaseRequest{
    private String name;
    private String phone;
}
