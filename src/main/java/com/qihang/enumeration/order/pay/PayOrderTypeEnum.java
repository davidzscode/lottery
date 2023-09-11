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
public enum PayOrderTypeEnum {
    
    RECHARGE("0", "充值"),
    FOOTBALL("1", "下单竞彩足球"),
    BASKETBALL("2", "下单竞彩篮球"),
    SINGLE("3", "下单北京单场"),
    ARRAY("4", "下单排列3"),
    
    PAYMENT("5", "提现"),
    
    FOOTBALL_AWARD("6", "竞彩足球派奖"),
    BASKETBALL_AWARD("7", "竞彩篮球派奖"),
    SINGLE_AWARD("8", "北京单场派奖"),
    ARRAY_AWARD("9", "排列3派奖"),
    
    FOOTBALL_REFUND("10", "竞彩足球退票"),
    BASKETBALL_REFUND("11", "竞彩篮球退票"),
    SINGLE_REFUND("12", "北京单场退票"),
    ARRAY_REFUND("13", "排列3退票"),
    
    ISSUING_REWARD("14", "发单返佣金"),
    
    ARRANGE("15", "下单排列5"),
    
    ARRANGE_AWARD("16", "排列5派奖"),
    
    ARRANGE_REFUND("17", "排列5退票"),
    
    SEVEN_STAR("18", "下单七星彩"),
    
    SEVEN_STAR_AWARD("19", "七星彩派奖"),
    
    SEVEN_STAR_REFUND("20", "七星彩退票"),
    
    VICTORY_DEFEAT("21", "下单足球14场"),
    
    VICTORY_DEFEAT_AWARD("22", "足球14场派奖"),
    
    VICTORY_DEFEAT_REFUND("23", "足球14场退票"),
    
    REN_JIU("24", "下单任选九"),
    
    REN_JIU_AWARD("25", "任选九派奖"),
    
    REN_JIU_REFUND("26", "任选九退票"),
    
    GRAND_LOTTO("27", "下单大乐透"),
    
    GRAND_LOTTO_AWARD("28", "大乐透派奖"),
    
    GRAND_LOTTO_REFUND("29", "大乐透退票"),
    SALER_REWARD("30", "销售返佣金"),
    
    USER_PAYMENT("98", "用户提现"),
    AUTO_RECHARGE("99", "自动充值");
    
    
    private String key;
    private String value;
}
