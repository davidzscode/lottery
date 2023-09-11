package com.qihang.hander;

import com.qihang.common.util.log.LogUtil;
import com.qihang.common.util.response.ResponseUtil;
import com.qihang.common.vo.BaseVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-14 14:33
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private LogUtil logUtil;

    /**
     * 用户登出返回结果
     * 这里应该让前端清除掉Token
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SecurityContextHolder.clearContext();
        ResponseUtil.ResponseMeg(response, new BaseVO());
    }
}
