package com.example.springboot.mapper;

import com.example.springboot.controller.DeepLearningModelPredict.request.DatasetRequest;
import com.example.springboot.entity.Dataset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DatasetMapper {

    void save(Dataset dataset);

    void update(Dataset dataset);

    List<Dataset> getListByCondition(DatasetRequest datasetRequest);
}

