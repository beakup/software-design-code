package com.example.springboot.controller;



import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.KnowledgePageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Knowledge;
import com.example.springboot.entity.Knowledge;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.service.IKnowledgeService;
import com.example.springboot.service.IKnowledgeService;
import com.example.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {
    
    @Autowired
    IKnowledgeService knowledgeService;

    private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "/kfiles/";
    private static final String BASE_FILE_PATH0 = System.getProperty("user.dir") + "/kcodefiles/";


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
            String url = "http://localhost:9090/api/knowledge/codefile/download/" + flag + "?&token=" + token;

            if (originalFilename.endsWith("pdf")||originalFilename.endsWith("PDF")||originalFilename.endsWith("py")||originalFilename.endsWith("PNG")||originalFilename.endsWith("png")||originalFilename.endsWith("JPG") || originalFilename.endsWith("jpg") || originalFilename.endsWith("pdf")) {
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
            throw new ServiceException("文件下载失败");
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
            String url = "http://localhost:9090/api/knowledge/file/download/" + flag + "?&token=" + token;
            if (originalFilename.endsWith("pdf")||originalFilename.endsWith("PDF")||originalFilename.endsWith("py")||originalFilename.endsWith("PNG")||originalFilename.endsWith("png")||originalFilename.endsWith("JPG") || originalFilename.endsWith("jpg") || originalFilename.endsWith("pdf")) {
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
        LoginDTO login = knowledgeService.login(request);
        return Result.success(login);
    }

    @PutMapping("/password")
    public Result password(@RequestBody PasswordRequest request) {
        knowledgeService.changePass(request);
        return Result.success();
    }

    @PostMapping("/save")
    public Result save(@RequestBody Knowledge obj) {
        knowledgeService.save(obj);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Knowledge obj) {
        knowledgeService.update(obj);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        knowledgeService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Knowledge obj = knowledgeService.getById(id);
        return Result.success(obj);
    }

    @GetMapping("/list")
    public Result list() {
        List<Knowledge> list = knowledgeService.list();
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result page(KnowledgePageRequest PageRequest) {
        return Result.success(knowledgeService.page(PageRequest));
    }
}
