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
public enum PayTypeEnum {

    ALIPAY("0", "支付宝"),
    BANK("1", "银行卡"),
    APP("2", "APP");

    private String key;
    private String value;
}
