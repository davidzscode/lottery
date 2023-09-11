package com.qihang.hander;

import com.qihang.common.vo.BaseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: qihang
 * @description: 全局异常处理
 * @time: 2022-08-02 11:52
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public BaseVO handleRequestValid(Exception ex) {
        if (ex instanceof BindException) {
            log.warn("请求参数异常【{}】", ex.getMessage());
            BaseVO base = new BaseVO(false, "500", ((BindException) ex).getFieldError().getDefaultMessage());
            return base;
        }
        ex.printStackTrace();
        return null;
    }
}
