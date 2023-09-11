package com.qihang.enumeration.follow;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-12 15:19
 */
@Getter
@AllArgsConstructor
public enum FollowEnum {
    FOLLOW("0", "关注"),
    CANCEL("1", "取消关注");

    private String key;
    private String value;
}
