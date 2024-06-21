package com.example.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.IAdminService;
import com.example.springboot.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.List;
// ModelRunner.java

import org.springframework.stereotype.Service;
        import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Service
public class ModelRunner {

    public String runModel(MultipartFile modelFile, MultipartFile imageFile) throws IOException {
        // 保存上传的模型文件和图像文件
        File tempModelFile = File.createTempFile("model", ".py");
        modelFile.transferTo(tempModelFile);

        File tempImageFile = File.createTempFile("image", ".png");//.jpg
        imageFile.transferTo(tempImageFile);

        // 调用Python脚本执行模型预测
        String command = "python " + tempModelFile.getAbsolutePath() + " " + tempImageFile.getAbsolutePath();
        Process process = Runtime.getRuntime().exec(command);

        // 读取脚本输出
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        // 等待脚本执行完成
        try {
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return output.toString();
            } else {
                return "Error: Model execution failed.";
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Error: Model execution interrupted.";
        } finally {
            // 清理临时文件
            tempModelFile.delete();
            tempImageFile.delete();
        }
    }
}


