package com.qihang.controller.winburden.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 足彩 胜负平表
 *
 * @author qihang
 * @since 2022-10-05
 */
@Data
@Accessors(chain = true)
public class WinBurdenMatchVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "编号")
    private String number;

    @ApiModelProperty(value = "颜色")
    private String color;

    @ApiModelProperty(value = "赛事")
    private String match;

    @ApiModelProperty(value = "主队")
    private String homeTeam;

    @ApiModelProperty(value = "不让球赔率")
    private List<Map<String, Object>> notLetOddsList;

    @ApiModelProperty(value = "客队")
    private String visitingTeam;

    @ApiModelProperty(value = "截止时间")
    private Date deadline;

    @ApiModelProperty(value = "分析链接")
    private String analysis;

    @ApiModelProperty(value = "选择项")
    private Integer choiceCount;

    @ApiModelProperty(value = "是否是胆")
    private Boolean isGallbladder;

}
