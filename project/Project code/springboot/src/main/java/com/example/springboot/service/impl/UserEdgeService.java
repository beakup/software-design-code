package com.example.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springboot.controller.dto.UserLoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.UserLoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.UserEdge;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserEdgeMapper;
import com.example.springboot.service.IUserEdgeService;
import com.example.springboot.utils.UserTokenUtils;
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
public class UserEdgeService implements IUserEdgeService {

    @Autowired
    UserEdgeMapper userEdgeMapper;

    private static final String DEFAULT_PASS = "123";
    private static final String PASS_SALT = "lxhahaha";

    @Override
    public List<UserEdge> list(){
        
        return userEdgeMapper.list();
    }

    @Override
    public PageInfo<UserEdge> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<UserEdge> users = userEdgeMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }

    /*@Override
    public void save(UserEdge obj) {
        //默认密码123
        if(StrUtil.isBlank(obj.getPassword())) {
            obj.setPassword(DEFAULT_PASS);
        }
        obj.setPassword(securePass(obj.getPassword()));//设置md5加密，加盐
        try{
            userEdgeMapper.save(obj);
        } catch (DuplicateKeyException e) {
            log.error("数据插入失败,username:{}",obj.getUsername(),e);
            throw new ServiceException("用户名重复");
        }
    }*/
    @Override
    public void save(UserEdge user) {
        //当做卡号来处理
        if(StrUtil.isBlank(user.getPassword())) {
            user.setPassword(DEFAULT_PASS);
        }
        Date date = new Date();
        user.setUsername(DateUtil.format(date, "yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        userEdgeMapper.save(user);
    }

    @Override
    public UserEdge getById(Integer id) {
        return userEdgeMapper.getById(id);
    }

    @Override
    public void update(UserEdge user) {
        user.setUpdatetime(new Date());
        userEdgeMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        userEdgeMapper.deleteById(id);
    }

    @Override//密码要考虑为null的可能
    public UserLoginDTO login(UserLoginRequest request) {
        request.setPassword(securePass(request.getPassword()));
        UserEdge userEdge = null;
        try {
            userEdge = userEdgeMapper.getBynameAndPassword(request.getName(),request.getPassword());
        } catch(Exception e) {
            log.error("根据用户名查询{}查询出错",request.getName());
            throw new ServiceException("用户名错误");
        }
        if(userEdge == null){
            throw new ServiceException("用户名或密码错误");
        }
        if(!userEdge.isStatus()){
            throw new ServiceException("当前用户已禁用，请联系管理员");
        }
        UserLoginDTO loginDTO = new UserLoginDTO();
        BeanUtils.copyProperties(userEdge,loginDTO);//只复制名字相同的属性
        //生成token
        String token = UserTokenUtils.genToken(String.valueOf(userEdge.getId()), userEdge.getPassword());
        loginDTO.setToken(token);
        return loginDTO;
    }

    @Override
    public void changePass(PasswordRequest request) {
        //注意对新密码加密
        request.setNewPass(securePass(request.getNewPass()));
        int count = userEdgeMapper.updatePassword(request);
        if (count <= 0) { //正常返回1
            throw new ServiceException("修改密码失败");
        }
    }

    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);
    }

    @Override
    public UserEdge getByUserId(Integer id) {
        return userEdgeMapper.getByUserId(id);
    }
}