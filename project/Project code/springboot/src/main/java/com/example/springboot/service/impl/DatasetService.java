package com.example.springboot.service.impl;

import com.example.springboot.controller.DeepLearningModelPredict.request.DatasetRequest;
import com.example.springboot.entity.Dataset;
import com.example.springboot.mapper.DatasetMapper;
import com.example.springboot.service.IDatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatasetService implements IDatasetService {
    @Autowired
    DatasetMapper datasetMapper;

    @Override
    public List<Dataset> getListByCondition(DatasetRequest datasetRequest) {
        return datasetMapper.getListByCondition(datasetRequest);
    }

    @Override
    public void save(Dataset dataset) {
        datasetMapper.save(dataset);
    }



    @Override
    public void update(Dataset dataset) {
        datasetMapper.update(dataset);
    }
}
