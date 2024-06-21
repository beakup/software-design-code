package com.example.springboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springboot.entity.UserEdge;
import com.example.springboot.service.IUserEdgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Slf4j
public class UserTokenUtils {

    private static IUserEdgeService staticUserEdgeService;

    @Resource
    private IUserEdgeService userEdgeService;

    @PostConstruct
    public void setUserEdgeService() {
        staticUserEdgeService = userEdgeService;
    }

    /**
     * 生成token
     *
     * @return
     */
    public static String genToken(String userEdgeId, String sign) {
        return JWT.create().withAudience(userEdgeId) // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期,要重新登录
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }

    public static String genToken(String userEdgeId, String sign, int days) {
        return JWT.create().withAudience(userEdgeId) // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetDay(new Date(), days)) // 2小时后token过期,要重新登录
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }
    /**
     * 获取当前登录的用户信息
     *
     * @return user对象
     *  /admin?token=xxxx
     */
    public static UserEdge getCurrentUser() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败， token: {}", token);
                return null;
            }
            String userEdgeId = JWT.decode(token).getAudience().get(0);
            
            return staticUserEdgeService.getById(Integer.valueOf(userEdgeId));
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败, token={}", token,  e);
            return null;
        }
    }
}

