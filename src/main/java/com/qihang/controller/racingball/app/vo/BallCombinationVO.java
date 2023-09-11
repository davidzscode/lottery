package com.qihang.controller.racingball.app.vo;

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
public class BallCombinationVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "场次")
    private String number;

    @ApiModelProperty(value = "主队")
    private String homeTeam;

    @ApiModelProperty(value = "客队")
    private String visitingTeam;

    @ApiModelProperty(value = "投注内容 格式为 平(3.05) ")
    private String content;
}
