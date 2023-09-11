package com.qihang.enumeration.withdrawal;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-08 21:14
 */
@Getter
@AllArgsConstructor
public enum ExamineEnum {

    UNAPPROVED("0", "未审核"),

    PAID("1", "已打款"),

    REFUSE("2", "拒绝");

    private String key;
    private String value;
}
