package com.example.springboot.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
public class FileUploadController {

    @PostMapping("/process-files")
    public void processFiles(@RequestParam("pythonFile") MultipartFile pythonFile,
                               @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            // 保存 Python 文件到指定位置
            String pythonFilePath = "D:\\Download\\codefiles";
            saveFile(pythonFile, pythonFilePath);

            // 保存图片文件到指定位置
            String imageFilePath = "D:\\Download\\imagefiles";
            saveFile(imageFile, imageFilePath);

            // 调用 Python 脚本进行处理
//            String result = runPythonScript(pythonFilePath, imageFilePath);
//
//            // 返回处理结果给前端
//            return result;
        } catch (IOException e) {
            // 处理上传异常
            e.printStackTrace();
            //return "处理文件时出错";
        }
    }

    private void saveFile(MultipartFile file, String filePath) throws IOException {
        Path destination = new File(filePath).toPath();
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
    }

    private String runPythonScript(String pythonFilePath, String imageFilePath) throws IOException {
        // 调用 Python 脚本进行处理，例如使用 ProcessBuilder 进行调用
        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonFilePath, imageFilePath);
        Process process = processBuilder.start();

        // 处理 Python 脚本的输出结果
        // 例如，可以使用 BufferedReader 读取脚本的输出并返回给前端
        // 这里只是简单地返回一个示例结果
        return "处理完成";
    }
}
