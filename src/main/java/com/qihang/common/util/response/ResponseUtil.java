package com.qihang.common.util.response;


import cn.hutool.json.JSONUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: qihang
 * @description: 响应处理工具类
 * @time: 2022-07-13 11:20
 */
@SuppressWarnings("all")
public class ResponseUtil {
    public static <T> void ResponseMeg(HttpServletResponse response, T t) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().write(JSONUtil.toJsonStr(t));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
