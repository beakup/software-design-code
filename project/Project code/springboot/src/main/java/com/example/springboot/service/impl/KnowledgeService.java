package com.example.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Knowledge;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.KnowledgeMapper;
import com.example.springboot.service.IAdminService;
import com.example.springboot.service.IKnowledgeService;
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
public class KnowledgeService implements IKnowledgeService {

    @Autowired
    KnowledgeMapper knowledgeMapper;

    private static final String DEFAULT_PASS = "123";
    private static final String PASS_SALT = "lxhahaha";

    @Override
    public List<Knowledge> list(){
        
        return knowledgeMapper.list();
    }

    @Override
    public PageInfo<Knowledge> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Knowledge> users = knowledgeMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }

    @Override
    public void save(Knowledge obj) {
        //默认密码123
        if(StrUtil.isBlank(obj.getPassword())) {
            obj.setPassword(DEFAULT_PASS);
        }
        obj.setPassword(securePass(obj.getPassword()));//设置md5加密，加密
        knowledgeMapper.save(obj);
    }

    @Override
    public Knowledge getById(Integer id) {
        return knowledgeMapper.getById(id);
    }

    @Override
    public void update(Knowledge user) {
        user.setUpdatetime(new Date());
        knowledgeMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        knowledgeMapper.deleteById(id);
    }

    @Override//密码要考虑为null的可能
    public LoginDTO login(LoginRequest request) {
        request.setPassword(securePass(request.getPassword()));
        Knowledge admin = knowledgeMapper.getByUsernameAndPassword(request.getUsername(),request.getPassword());
        if(admin == null){
            throw new ServiceException("用户名或密码错误");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin,loginDTO);//只复制名字相同的属性
        //生成token
        String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());
        loginDTO.setToken(token);
        return loginDTO;
    }

    @Override
    public void changePass(PasswordRequest request) {
        //注意对新密码加密
        request.setNewPass(securePass(request.getNewPass()));
        int count = knowledgeMapper.updatePassword(request);
        if (count <= 0) { //正常返回1
            throw new ServiceException("修改密码失败");
        }
    }

    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);
    }
}