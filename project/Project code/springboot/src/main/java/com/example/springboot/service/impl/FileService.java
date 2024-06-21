package com.example.springboot.service.impl;

import com.example.springboot.entity.Files;
import com.example.springboot.mapper.FileMapper;
import com.example.springboot.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FileService implements IFileService {
    @Autowired
    FileMapper fileMapper;


    @Override
    public void save(Files files) {
        fileMapper.save(files);

    }

    @Override
    public List<Files> list(String md5) {
        return fileMapper.list(md5);
    }
}
