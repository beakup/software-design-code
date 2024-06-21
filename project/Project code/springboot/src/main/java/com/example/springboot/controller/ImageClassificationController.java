package com.example.springboot.controller;


// ModelController.java

import com.example.springboot.service.impl.ModelRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/api")
public class ImageClassificationController {
    private Map<String, ClassificationResult> imageResults = new HashMap<>(); // 存储图像和对应的分类结果


    private String saveImageLocally(MultipartFile imageFile) {
        String uploadDir = "D:\\信息系统实践\\Project code\\imageFiles"; // 上传目录的路径

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // 创建目录
        }

        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        File newFile = new File(directory.getAbsolutePath() + File.separator + fileName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            fileOutputStream.write(imageFile.getBytes());
            fileOutputStream.close();
            return newFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("无法保存上传的图像文件：" + e.getMessage());
        }
    }

    @PostMapping("/classify")
    public ResponseEntity<Map<String, Double>> classifyImage() {
        try {
            // 生成随机的两个概率值（相加为1）
            double randomProbability = new Random().nextDouble();
            double class1Probability = Math.round(randomProbability * 100) / 100.0;
            double class2Probability = 1.0 - class1Probability;

            // 构建结果Map
            Map<String, Double> result = new HashMap<>();
            result.put("class1Probability", class1Probability);
            result.put("class2Probability", class2Probability);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}

class ClassificationResult {
    private double class1Probability;
    private double class2Probability;

    public ClassificationResult(double class1Probability, double class2Probability) {
        this.class1Probability = class1Probability;
        this.class2Probability = class2Probability;
    }

    public double getClass1Probability() {
        return class1Probability;
    }

    public double getClass2Probability() {
        return class2Probability;
    }
}
