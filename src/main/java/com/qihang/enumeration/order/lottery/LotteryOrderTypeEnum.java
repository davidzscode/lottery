package com.qihang.enumeration.order.lottery;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: qihang
 * @description:
 * @time: 2022/10/10 15:44
 */
@Getter
@AllArgsConstructor
public enum LotteryOrderTypeEnum {
    FOOTBALL("0", "竞彩足球"),
    BASKETBALL("1", "竞彩篮球"),
    SINGLE("2", "北京单场"),
    ARRAY("3", "排列3"),
    ARRANGE("4", "排列5"),

    SEVEN_STAR("5", "七星彩"),

    VICTORY_DEFEAT("6", "足球14场"),

    REN_JIU("7", "任选九"),

    GRAND_LOTTO("8", "大乐透");
    private String key;
    private String value;

    public static LotteryOrderTypeEnum valueOFS(String key) {
        for (LotteryOrderTypeEnum lotteryOrderTypeEnum : LotteryOrderTypeEnum.values()) {
            if (ObjectUtil.equal(key, lotteryOrderTypeEnum.key)) {
                return lotteryOrderTypeEnum;
            }
        }
        return null;
    }
}
