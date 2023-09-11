package com.qihang.controller.basketball.vo;

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
public class BasketballVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属于哪天的比赛时间")
    private String startTime;

    @ApiModelProperty(value = "比赛数量")
    private Integer count;

    @ApiModelProperty(value = "比赛队伍信息")
    List<BasketballMatchVO> basketballMatchList;
}
