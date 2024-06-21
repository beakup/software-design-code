package com.example.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Prediction;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.mapper.PredictionMapper;
import com.example.springboot.service.IAdminService;
import com.example.springboot.service.IPredictionService;
import com.example.springboot.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PredictionService implements IPredictionService {

    @Autowired
    PredictionMapper predictionMapper;

    private static final String DEFAULT_PASS = "123";
    private static final String PASS_SALT = "lxhahaha";

    @Override
    public List<Prediction> list(){
        
        return predictionMapper.list();
    }

    @Override
    public PageInfo<Prediction> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Prediction> users = predictionMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }

    @Override
    public void save(Prediction obj) {
        //默认密码123
        if(StrUtil.isBlank(obj.getPassword())) {
            obj.setPassword(DEFAULT_PASS);
        }
        obj.setPassword(securePass(obj.getPassword()));//设置md5加密，加密
        predictionMapper.save(obj);
    }

    @Override
    public Prediction getById(Integer id) {
        return predictionMapper.getById(id);
    }

    @Override
    public void update(Prediction user) {
        user.setUpdatetime(new Date());
        predictionMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        predictionMapper.deleteById(id);
    }

    @Override//密码要考虑为null的可能
    public LoginDTO login(LoginRequest request) {
        request.setPassword(securePass(request.getPassword()));
        Prediction prediction = predictionMapper.getByUsernameAndPassword(request.getUsername(),request.getPassword());
        if(prediction == null){
            throw new ServiceException("用户名或密码错误");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(prediction,loginDTO);//只复制名字相同的属性
        //生成token
        String token = TokenUtils.genToken(String.valueOf(prediction.getId()), prediction.getPassword());
        loginDTO.setToken(token);
        return loginDTO;
    }

    @Override
    public void changePass(PasswordRequest request) {
        //注意对新密码加密
        request.setNewPass(securePass(request.getNewPass()));
        int count = predictionMapper.updatePassword(request);
        if (count <= 0) { //正常返回1
            throw new ServiceException("修改密码失败");
        }
    }

    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);
    }
}