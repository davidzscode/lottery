package com.qihang.enumeration.line;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-22 23:33
 */
@Getter
@AllArgsConstructor
public enum LineEnum {
    UPPER("0", "上架"),
    LOWER("1", "下架");

    private String key;
    private String value;
}
