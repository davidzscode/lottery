package com.qihang.controller.order.app.lottery.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-22 10:08
 */
@Data
@Accessors(chain = true)
public class BallInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private String number;

    @ApiModelProperty(value = "主队")
    private String homeTeam;

    @ApiModelProperty(value = "半全场结果")
    private String halfFullCourt;

    @ApiModelProperty(value = "让球")
    private String letBall;

    @ApiModelProperty(value = "客队")
    private String visitingTeam;

    @ApiModelProperty(value = "下注内容")
    private String content;

    @ApiModelProperty(value = "开奖结果")
    private String award;

    @ApiModelProperty(value = "北单赔率")
    private String bonusOdds;
}
