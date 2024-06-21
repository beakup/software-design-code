package com.example.springboot.service.impl;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.example.springboot.controller.DeepLearningModelPredict.request.DetectRequest;
import com.example.springboot.service.IImageService;
import com.example.springboot.common.ServiceConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;


import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImageService implements IImageService {

    private Logger logger= LoggerFactory.getLogger(ImageService.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String IMAGE_QUEUE = "imageQueue";

    private static final String CONSULT_OUT = "consultOut";

    private static final String IMAGE_KEY = "imageKey";
    private static final String IMAGE_URL = "imageUrl";
    private static final String MODEL_NAME = "ModelName";
    private static final String DATA_SET = "DataSet";
    private static final String TASK = "Task";

    private static final Set<Long> TIME_INTERVALS = new HashSet<Long>() {
        {
            this.add(100L);
            this.add(200L);
            this.add(400L);
            this.add(800L);
            this.add(1200L);
            this.add(1600L);
        }
    };

    @Override
    public String detectInQueue(DetectRequest detectRequest) throws Exception {
        //step 1. Is the queue overrun
        if (stringRedisTemplate.opsForList().size(IMAGE_QUEUE) > ServiceConstants.THREAD_STACK_SIZE) {
            logger.error("thread stack size is out of limit");
            throw new Exception("System busy,Please try again later");
        }

        //step 2. Put imageUrl in queue
        Map<String, Object> imageInfo = new HashMap<>();
        String imageKey =  UUID.randomUUID().toString(); // (IdUtil.fastSimpleUUID() + StrUtil.DOT);
        imageInfo.put(IMAGE_KEY, imageKey);
        imageInfo.put(IMAGE_URL, detectRequest.getInputUrl());
        imageInfo.put(MODEL_NAME, detectRequest.getModelName() );
        imageInfo.put(DATA_SET, detectRequest.getDataSet() );
        imageInfo.put(TASK, detectRequest.getTask() );
        stringRedisTemplate.opsForList().leftPush(IMAGE_QUEUE, JSON.toJSONString(imageInfo));

        //step 3. Get the result
        for (Long interval : TIME_INTERVALS) {
            try {
                Thread.currentThread().sleep(interval.longValue());
                Object consultResult = stringRedisTemplate.opsForHash().get(imageKey, CONSULT_OUT);
                if (consultResult != null) {
                    //delete result in cache
                    stringRedisTemplate.delete(imageKey);
                    return (String) consultResult;
                }
            } catch (InterruptedException e) {
                logger.error("Seg service thread error");
            }
        }
        return null;
    }

}
