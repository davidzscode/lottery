package com.qihang.controller.racingball.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-22 10:08
 */
@Data
@Accessors(chain = true)
public class BallCalculationVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总注数")
    private Integer notes;

    @ApiModelProperty(value = "预计最小金额")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "预计最大金额")
    private BigDecimal maxPrice;

    @ApiModelProperty(value = "每一注的拼成的结果 例如  第一注 胜,胜,7+,胜-胜,7:0 第一注 胜,胜,1,平-胜,1:0 第三注 胜,胜,2,胜-胜,2:0")
    private String award;

    @ApiModelProperty(value = "（平均优化）每一注组合")
    List<BallOptimizationVO> averageOptimizationList;

    @ApiModelProperty(value = "（博热优化）每一注组合")
    List<BallOptimizationVO> heatOptimizationList;

    @ApiModelProperty(value = "（博冷优化）每一注组合")
    List<BallOptimizationVO> coldOptimizationList;
}
