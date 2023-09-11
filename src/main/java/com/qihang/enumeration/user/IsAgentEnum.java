package com.qihang.enumeration.user;

import lombok.AllArgsConstructor;

/**
 * 用户类型 1=店主  2=销售 0=用户
 * @author lihv
 * @since 2023/7/28
 */
@AllArgsConstructor
public enum IsAgentEnum {
    shopkeeper("1", "店主"),
    Salesperson("2", "销售"),
    User("0", "用户");
    private String key;
    private String value;
    
    public String getKey() {
        return key;
    }
}
