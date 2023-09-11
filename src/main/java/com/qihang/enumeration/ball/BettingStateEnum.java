package com.qihang.enumeration.ball;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-15 17:30
 */
@Getter
@AllArgsConstructor
public enum BettingStateEnum {

    NO("0", "不可下注"),

    YES("1", "可下注");

    private String key;
    private String value;
}
