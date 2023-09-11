package com.qihang.annotation;

import java.lang.annotation.*;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-12 23:16
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TenantIgnore {

}
