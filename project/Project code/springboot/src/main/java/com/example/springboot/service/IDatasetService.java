package com.example.springboot.service;

import com.example.springboot.controller.DeepLearningModelPredict.request.DatasetRequest;
import com.example.springboot.entity.Dataset;

import java.util.List;

public interface IDatasetService {
    List<Dataset> getListByCondition(DatasetRequest datasetRequest);

    void save(Dataset dataset);

    void update(Dataset dataset);
}
