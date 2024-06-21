package com.example.springboot.service;

import com.example.springboot.entity.Files;

import java.util.List;

public interface IFileService {

    void save(Files files);
    List<Files> list(String md5);

}
