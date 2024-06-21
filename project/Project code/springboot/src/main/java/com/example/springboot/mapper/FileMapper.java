package com.example.springboot.mapper;

import com.example.springboot.entity.Files;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {
    void save(Files files);

    List<Files> list(@Param("md5") String md5);
}
