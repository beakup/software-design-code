package com.example.springboot.controller;



import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerResponse;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.PredictionPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Prediction;
import com.example.springboot.service.IPredictionService;
import com.example.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


@Slf4j
@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/prediction")
public class PredictionController {

    @Autowired
    IPredictionService predictionService;

    private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "/files/";
    private static final String BASE_FILE_PATH0 = System.getProperty("user.dir") + "/codefiles/";

//添加模型
    @PostMapping("/codefile/upload")
    public Result uploadcodeFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        long flag = System.currentTimeMillis();
        String filePath = BASE_FILE_PATH0 + flag + "_" + originalFilename;
        try { //防止失败，提前捕获错误
            FileUtil.mkParentDirs(filePath);  //创建父级目录
            file.transferTo(FileUtil.file(filePath));
            Admin currentAdmin = TokenUtils.getCurrentAdmin();
            String token = TokenUtils.genToken(currentAdmin.getId().toString(), currentAdmin.getPassword(),15);
            String url = "http://localhost:9090/api/prediction/codefile/download/" + flag + "?&token=" + token;

            if (originalFilename.endsWith("pdf")||originalFilename.endsWith("py")||originalFilename.endsWith("PNG")||originalFilename.endsWith("png")||originalFilename.endsWith("JPG") || originalFilename.endsWith("jpg") || originalFilename.endsWith("pdf")) {
                url += "&play=1";
            }
            return Result.success(url);
        } catch (Exception e) {
            log.info("文件上传失败", e);
        }
        return Result.error("上传文件失败");
    }

    @GetMapping("/codefile/download/{flag}") //和上面这个一一对应
    public void downloadcode(@PathVariable String flag, @RequestParam(required = false) String play, HttpServletResponse response) {
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(BASE_FILE_PATH0);
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse(""); //时间戳
        try {
            if(StrUtil.isNotEmpty(fileName)) {
                String realName = fileName.substring(fileName.indexOf("_") + 1);
                if("1".equals(play)) {
                    response.addHeader("Content-Disposition","inline;filename=" + URLEncoder.encode(realName,"UTF-8"));
                } else {
                    response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(realName,"UTF-8"));
                }
                byte[] bytes = FileUtil.readBytes(BASE_FILE_PATH0 + fileName);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e) {
            log.error("文件下载失败", e);
        }
    }


//添加图片
    @PostMapping("/file/upload")
    public Result uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        long flag = System.currentTimeMillis();
        String filePath = BASE_FILE_PATH + flag + "_" + originalFilename;
        try { //防止失败，提前捕获错误
            FileUtil.mkParentDirs(filePath);  //创建父级目录
            file.transferTo(FileUtil.file(filePath));
            Admin currentAdmin = TokenUtils.getCurrentAdmin();
            String token = TokenUtils.genToken(currentAdmin.getId().toString(), currentAdmin.getPassword(),15);
            String url = "http://localhost:9090/api/prediction/file/download/" + flag + "?&token=" + token;
            if (originalFilename.endsWith("pdf")||originalFilename.endsWith("py")||originalFilename.endsWith("PNG")||originalFilename.endsWith("png")||originalFilename.endsWith("JPG") || originalFilename.endsWith("jpg") || originalFilename.endsWith("pdf")) {
                url += "&play=1";
            }
            return Result.success(url);
        } catch (Exception e) {
            log.info("文件上传失败", e);
        }
        return Result.error("上传文件失败");
    }

    @GetMapping("/file/download/{flag}") //和上面这个一一对应
    public void download(@PathVariable String flag, @RequestParam(required = false) String play, HttpServletResponse response) {
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(BASE_FILE_PATH);
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse(""); //时间戳
        try {
            if(StrUtil.isNotEmpty(fileName)) {
                String realName = fileName.substring(fileName.indexOf("_") + 1);
                if("1".equals(play)) {
                    response.addHeader("Content-Disposition","inline;filename=" + URLEncoder.encode(realName,"UTF-8"));
                } else {
                    response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(realName,"UTF-8"));
                }
                byte[] bytes = FileUtil.readBytes(BASE_FILE_PATH + fileName);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e) {
            log.error("文件下载失败", e);
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request) {
        LoginDTO login = predictionService.login(request);
        return Result.success(login);
    }

    @PutMapping("/password")
    public Result password(@RequestBody PasswordRequest request) {
        predictionService.changePass(request);
        return Result.success();
    }

    @PostMapping("/save")
    public Result save(@RequestBody Prediction obj) {
        predictionService.save(obj);
        return Result.success();
    }

    @PostMapping("/process-files")
    public String processFiles(@RequestParam("pythonFile") String pythonFile,
                               @RequestParam("imageFile") String imageFile) {
        try {
            String result = runPythonScript(pythonFile, imageFile);

            // 返回处理结果给前端
            return result;
        } catch (IOException e) {
            // 处理上传异常
            e.printStackTrace();
            return "处理文件时出错";
        }
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

    @PutMapping("/update")
    public Result update(@RequestBody Prediction obj) {
        predictionService.update(obj);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        predictionService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Prediction obj = predictionService.getById(id);
        return Result.success(obj);
    }

    @GetMapping("/list")
    public Result list() {
        List<Prediction> list = predictionService.list();
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result page(PredictionPageRequest PageRequest) {
        return Result.success(predictionService.page(PageRequest));
    }
}
