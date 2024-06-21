package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class KnowledgeRequest extends BaseRequest{
    private Integer id;
    private String messagename;
    private String message;
}
