package com.qihang.controller.documentary.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author qihang
 */
@Data
public class UserRankingVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "中奖数量")
    private Integer wincCount;

    @ApiModelProperty(value = "连红数量")
    private Integer lianHongCount;

    @ApiModelProperty(value = "胜率")
    private Integer win;

    @ApiModelProperty(value = "中奖的总金额")
    private BigDecimal totalPrice;
}
