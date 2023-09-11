package com.qihang.config;

import com.qihang.common.vo.BaseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义统一异常处理
 * @author lihv
 * @since 2023/7/30
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseVO exceptionHandler(Exception e) {
        log.error("全局异常捕获", e);
        return new BaseVO(false, "-1", "业务处理异常");
    }
}
