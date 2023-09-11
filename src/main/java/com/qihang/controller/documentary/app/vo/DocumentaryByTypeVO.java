package com.qihang.controller.documentary.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-11 21:56
 */
@Data
public class DocumentaryByTypeVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "跟单id")
    private Integer documentaryId;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "描述")
    private String describe;

    @ApiModelProperty(value = "预计回报倍数")
    private BigDecimal remuneration;

    @ApiModelProperty(value = "截止时间")
    private Long endTime;

    @ApiModelProperty(value = "彩票名称")
    private String name;

    @ApiModelProperty(value = "logo")
    private String url;

    @ApiModelProperty(value = "下注金额")
    private BigDecimal price;

    @ApiModelProperty(value = "跟单总共人数")
    private Integer quantity;

    @ApiModelProperty(value = "跟单总金额")
    private BigDecimal totalPrice;
}
