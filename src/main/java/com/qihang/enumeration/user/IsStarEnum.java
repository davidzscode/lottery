package com.qihang.enumeration.user;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 标记是否星级用户
 * 0表示非星级用户
 * 1表示星级用户
 * */
@Getter
@AllArgsConstructor
public enum IsStarEnum {
    StarUser(1, "星级用户"),
    NotStarUser(0, "非星级用户");
    private Integer key;
    private String value;
}
