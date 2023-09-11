package com.qihang.controller.winburden.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 足彩 胜负平表
 *
 * @author qihang
 * @since 2022-10-05
 */
@Data
@Accessors(chain = true)
public class WinBurdenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "期号")
    private String issueNo;

    @ApiModelProperty(value = "比赛队伍信息")
    List<WinBurdenMatchVO> winBurdenMatchList;
}
