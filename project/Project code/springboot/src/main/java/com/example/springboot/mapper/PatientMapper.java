package com.example.springboot.mapper;

import com.example.springboot.entity.Patient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PatientMapper {
    void save(Patient patient);

    Patient getById(@Param("id") Integer id);
}
