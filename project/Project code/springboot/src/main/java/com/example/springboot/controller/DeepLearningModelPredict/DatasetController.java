package com.example.springboot.controller.DeepLearningModelPredict;

import cn.hutool.core.date.DateUtil;
import com.example.springboot.common.Result;
import com.example.springboot.controller.DeepLearningModelPredict.request.DatasetRequest;
import com.example.springboot.entity.Dataset;
import com.example.springboot.service.IDatasetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/dataset")
public class DatasetController {
    @Autowired
    private IDatasetService datasetService;

    @GetMapping("/list")
    private Result getListByTask(@RequestParam(value = "name",required = false) String name,
                                 @RequestParam(value = "task",required = false) String task,
                                 @RequestParam(value = "inform",required = false) String inform
                                 )
    {
        DatasetRequest datasetRequest = new DatasetRequest();
        datasetRequest.setName(name);
        datasetRequest.setTask(task);
        datasetRequest.setInform(inform);
        return Result.success(datasetService.getListByCondition(datasetRequest));
    }

    @PostMapping("/save")
    private Result save(@RequestBody Dataset dataset){
        if (dataset.getName()!=null){
            DatasetRequest datasetRequest = new DatasetRequest();
            datasetRequest.setName(dataset.getName());
            List<Dataset> datasets=datasetService.getListByCondition(datasetRequest);
            if(datasets.isEmpty()){
                dataset.setCreatetime(DateUtil.now());
                datasetService.save(dataset);
            }
            else {
                datasetService.update(dataset);
            }
            return Result.success();
        }

        return Result.error("name 不能为空");
    }
}
