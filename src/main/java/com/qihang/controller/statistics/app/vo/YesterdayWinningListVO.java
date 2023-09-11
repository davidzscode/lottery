package com.qihang.controller.statistics.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lihv
 * @since 2023/7/29
 */
@Data
public class YesterdayWinningListVO extends BaseVO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "中奖总金额")
    private BigDecimal totalWinningPrice;
    
    @ApiModelProperty(value = "中奖用户id")
    private Integer userId;

    @ApiModelProperty(value = "中奖用户名")
    private String userNickName;

    @ApiModelProperty(value = "中奖用户头像")
    private String avatar;
}
