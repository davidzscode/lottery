package com.qihang.controller.football.vo;

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
public class FootballMatchVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "编号")
    private String number;

    @ApiModelProperty(value = "颜色")
    private String color;

    @ApiModelProperty(value = "赛事")
    private String match;

    @ApiModelProperty(value = "让球")
    private String letBall;

    @ApiModelProperty(value = "让球赔率")
    private List<Map<String, Object>> letOddsList;

    @ApiModelProperty(value = "不让球赔率")
    private List<Map<String, Object>> notLetOddsList;

    @ApiModelProperty(value = "总进球赔率")
    private List<Map<String, Object>> goalOddsList;

    @ApiModelProperty(value = "半全场赔率")
    private List<Map<String, Object>> halfWholeOddsList;

    @ApiModelProperty(value = "比分赔率")
    private List<Map<String, Object>> scoreOddsList;

    @ApiModelProperty(value = "主队")
    private String homeTeam;

    @ApiModelProperty(value = "客队")
    private String visitingTeam;

    @ApiModelProperty(value = "是否单关 0 否 1 是")
    private String isSingle;

    @ApiModelProperty(value = "截止时间")
    private Date deadline;

    @ApiModelProperty(value = "分析链接")
    private String analysis;

    @ApiModelProperty(value = "选择项")
    private Integer choiceCount;
}
