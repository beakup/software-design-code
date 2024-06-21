package com.example.springboot.controller.DeepLearningModelPredict;

import com.example.springboot.common.Result;
import com.example.springboot.controller.DeepLearningModelPredict.request.ResultHistoryPageRequst;
import com.example.springboot.entity.ResultHistory;
import com.example.springboot.entity.UserEdge;
import com.example.springboot.service.IResultHistoryService;
import com.example.springboot.utils.UserTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/resulthistory")
public class ResultHistoryController {

    @Autowired
    private IResultHistoryService resultHistoryService;

    @PostMapping
    public Result save(@RequestBody ResultHistory resultHistory){
        resultHistoryService.save(resultHistory);
        return Result.success();
    }

    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id){
        resultHistoryService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public ResultHistory getById(@PathVariable Integer id){
        return resultHistoryService.getById(id);
    }


    @PostMapping("/page")
    public Result page(@RequestBody ResultHistoryPageRequst resultHistoryPageRequst){
        switch (resultHistoryPageRequst.getOrderField()) {
            case "1":
                resultHistoryPageRequst.setOrderField1("patient_id");
                resultHistoryPageRequst.setOrderField2("createtime");
                resultHistoryPageRequst.setOrderType("desc");
                break;
            case "3":
                resultHistoryPageRequst.setOrderField1("createtime");
                resultHistoryPageRequst.setOrderField2("patient_id");
                resultHistoryPageRequst.setOrderType("");
                break;
            default:
                resultHistoryPageRequst.setOrderField1("createtime");
                resultHistoryPageRequst.setOrderField2("patient_id");
                resultHistoryPageRequst.setOrderType("desc");
        }
        return Result.success(resultHistoryService.page(resultHistoryPageRequst));
    }

}
