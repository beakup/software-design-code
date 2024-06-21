package com.example.springboot.controller.DeepLearningModelPredict;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.example.springboot.common.Result;
import com.example.springboot.controller.DeepLearningModelPredict.dto.DetectDTO;
import com.example.springboot.controller.DeepLearningModelPredict.request.DatasetRequest;
import com.example.springboot.controller.DeepLearningModelPredict.request.DetectRequest;
import com.example.springboot.controller.DeepLearningModelPredict.request.ModelRequest;
import com.example.springboot.entity.*;
import com.example.springboot.service.IDatasetService;
import com.example.springboot.service.IImageService;
import com.example.springboot.service.IModelService;
import com.example.springboot.service.IResultHistoryService;
import com.example.springboot.utils.TokenUtils;
import com.example.springboot.utils.UserTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Slf4j
@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/detectImage")
public class DetectImageController {

    @Value("${server.ip}")
    private String serverIp;

    @Autowired
    private IImageService imageConsultService;

    @Autowired
    private IDatasetService datasetService;

    @Autowired
    private IResultHistoryService resultHistoryService;

    @PostMapping("/OutWithCache")
    public Result segOutWithCache(@RequestBody DetectRequest detectRequest) throws Exception {

        // step 1. detect image
        String detectOut = imageConsultService.detectInQueue(detectRequest);

        if (detectRequest.getTask().equals("seg")){
            String result = "http://" + serverIp + ":9090/api/file/" + detectOut;
            saveResult(detectRequest,result);
            return Result.success(result);
        }
        else {
            DatasetRequest datasetRequest = new DatasetRequest();
            datasetRequest.setName(detectRequest.getDataSet());
            List<Dataset> ones = datasetService.getListByCondition(datasetRequest);
            saveResult(detectRequest,detectOut+ones.get(0).getInform());

            List<Double> probs=Arrays.stream(detectOut.split(",")).
                    map(s->Double.parseDouble(s)).collect(Collectors.toList());
            List<String> names = Arrays.stream(ones.get(0).getInform().split(",")).
                    collect(Collectors.toList());

            List<DetectDTO> result = new ArrayList<>();
            for (int i = 0; i<probs.size(); i++){
                DetectDTO one = new DetectDTO();
                one.setName(names.get(i));
                one.setValue(((double) (int) (probs.get(i)*10000))/100);
                result.add(one);
            }
            return Result.success(result);
        }
        // step 3. return seg result page
    }

    private void saveResult(DetectRequest detectRequest, String detectOut){
        ResultHistory resultHistory = new ResultHistory();
        BeanUtil.copyProperties(detectRequest, resultHistory, true);
        resultHistory.setInform(detectOut);
        resultHistory.setCreatetime(DateUtil.now());
        resultHistoryService.save(resultHistory);
    }
}
