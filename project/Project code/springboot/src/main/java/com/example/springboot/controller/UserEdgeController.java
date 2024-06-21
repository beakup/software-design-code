package com.example.springboot.controller;



import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.UserLoginDTO;
import com.example.springboot.controller.request.UserEdgePageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.controller.request.UserLoginRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.UserEdge;
import com.example.springboot.service.IUserEdgeService;
import com.example.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
@Slf4j //定义log
@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/userEdge")
public class UserEdgeController {
    @Autowired
    IUserEdgeService userEdgeService;

    private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "/files/";


    @PostMapping("/login")
    public Result login(@RequestBody UserLoginRequest request) {
        UserLoginDTO login = userEdgeService.login(request);

        return Result.success(login);
    }

    @PutMapping("/password")
    public Result password(@RequestBody PasswordRequest request) {
        userEdgeService.changePass(request);
        return Result.success();
    }

    @PostMapping("/save")
    public Result save(@RequestBody UserEdge obj) {
        userEdgeService.save(obj);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody UserEdge obj) {
        userEdgeService.update(obj);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userEdgeService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        UserEdge obj = userEdgeService.getById(id);
        return Result.success(obj);
    }

    @GetMapping("/list")
    public Result list() {
        List<UserEdge> list = userEdgeService.list();
        return Result.success(list);
    }
    @GetMapping("/userList/{id}")
    public Result userList(@PathVariable Integer id) {
        UserEdge obj = userEdgeService.getByUserId(id);
        return Result.success(obj);
    }

    @GetMapping("/page")
    public Result page(UserEdgePageRequest PageRequest) {
        return Result.success(userEdgeService.page(PageRequest));
    }

}
