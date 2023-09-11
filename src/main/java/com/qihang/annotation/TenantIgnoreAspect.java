package com.qihang.annotation;

import com.qihang.tenant.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 过滤多租户
 * @author: qihang
 * @description:
 * @time: 2022-11-12 23:17
 */
@Aspect
@Slf4j
@Component
public class TenantIgnoreAspect {
    
    @Around("@annotation(tenantIgnore)")
    public Object around(ProceedingJoinPoint joinPoint, TenantIgnore tenantIgnore) throws Throwable {
        Boolean oldIgnore = TenantContextHolder.isIgnore();
        try {
            TenantContextHolder.setIgnore(true);
            log.info("tenantIgnore >> old:{},now:{}", oldIgnore, TenantContextHolder.isIgnore());
            // 执行逻辑
            return joinPoint.proceed();
        } finally {
            TenantContextHolder.setIgnore(oldIgnore);
        }
    }
}
