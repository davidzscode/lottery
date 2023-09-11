package com.qihang.enumeration.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-14 14:21
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    NORMAL("0", "正常"),
    DISABLE("1", "禁用");

    private String status;
    private String name;
}
