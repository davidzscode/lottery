package com.qihang.enumeration.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-16 20:09
 */
@Getter
@AllArgsConstructor
public enum SexEnum {
    MAN("0","男"),
    WOMAN("1","女"),
    UNKNOWN("2","未知");
    private String key;
    private String value;
}
