package com.qihang.controller.racingball.app.dto;

import com.qihang.controller.basketball.dto.BasketballMatchDTO;
import com.qihang.controller.beidan.dto.BeiDanMatchDTO;
import com.qihang.controller.football.dto.FootballMatchDTO;
import com.qihang.controller.winburden.dto.WinBurdenMatchDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-22 10:28
 */
@Data
@Accessors(chain = true)
public class BallCalculationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "选中的足球比赛列表")
    private List<FootballMatchDTO> footballMatchList;

    @ApiModelProperty(value = "选中的蓝球比赛列表")
    private List<BasketballMatchDTO> basketballMatchList;

    @ApiModelProperty(value = "选中的北单比赛列表")
    private List<BeiDanMatchDTO> beiDanMatchList;

    @ApiModelProperty(value = "选中的胜负彩比赛列表")
    private List<WinBurdenMatchDTO> winBurdenMatchList;

    @ApiModelProperty(value = "倍数")
    @NotNull(message = "倍数不能为空")
    private Integer multiple;

    @ApiModelProperty(value = "总注数")
    private Integer notes;

    @ApiModelProperty(value = "类型 0足球 1篮球 2北单 6胜负彩")
    @NotBlank(message = "类型不能为空")
    private String type;

    @ApiModelProperty(value = "预测奖金")
    private BigDecimal forecast;

    @ApiModelProperty(value = "类型")
    @NotEmpty(message = "类型不能为空")
    private List<Integer> pssTypeList;

    @ApiModelProperty(value = "方案详情")
    private String schemeDetails;
}
