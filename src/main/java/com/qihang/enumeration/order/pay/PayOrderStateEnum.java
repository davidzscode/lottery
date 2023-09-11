package com.qihang.enumeration.order.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-12 15:52
 */
@Getter
@AllArgsConstructor
public enum PayOrderStateEnum {

    UNPAID("0", "未支付"),

    PAID("1", "已支付"),
    PAYMENT("2", "已打款");

    private String key;
    private String value;
}
