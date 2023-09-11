package com.qihang.enumeration.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 标记用户是否充值过
 * 0 表示未充值过
 * 1 表示充值过
 */
@Getter
@AllArgsConstructor
public enum IsRechargeEnum {
    RechargeUser(1, "已充值用户"),
    NotRechargeUser(0, "未充值用户");
    private Integer key;
    private String value;
}
