package com.qihang.hander;

import com.qihang.common.util.response.ResponseUtil;
import com.qihang.common.vo.BaseVO;
import com.qihang.enumeration.error.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 用户未登录处理类
 * @Author: qihang
 * @Date: 2020/4/28
 **/
@Component
@Slf4j
public class UserNotLoginHandler implements AuthenticationEntryPoint {
    /**
     * 用户未登录返回结果
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        log.warn("用户未登录返回结果", exception);
        ResponseUtil.ResponseMeg(response, new BaseVO(false, ErrorCodeEnum.E0758.getKey(), ErrorCodeEnum.E0758.getValue()));
    }
}