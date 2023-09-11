package com.qihang.enumeration.order.lottery;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-12 15:52
 */
@Getter
@AllArgsConstructor
public enum LotteryOrderStateEnum {

    TO_BE_ISSUED("0", "待出票"),
    TO_BE_AWARDED("1", "待开奖"),
    FAIL_TO_WIN("2", "未中奖"),
    WAITING_AWARD("3", "待派奖"),

    ALREADY_AWARD("4", "已派奖"),

    REFUSE("5", "拒绝"),

    REFUND ("6", "退票");

    private String key;
    private String value;
}
