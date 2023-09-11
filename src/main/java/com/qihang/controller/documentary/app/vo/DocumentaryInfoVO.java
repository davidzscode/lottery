package com.qihang.controller.documentary.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-11 21:56
 */
@Data
public class DocumentaryInfoVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "跟单id")
    private Integer documentaryId;

    @ApiModelProperty(value = "彩票名称")
    private String name;

    @ApiModelProperty(value = "logo")
    private String url;

    @ApiModelProperty(value = "下注金额")
    private BigDecimal price;

    @ApiModelProperty(value = "跟单总共人数")
    private Integer quantity;

    @ApiModelProperty(value = "0 可以跟单 1 待开奖 2 未中奖 3 已中奖")
    private String type;

    @ApiModelProperty(value = "中奖金额")
    private BigDecimal winPrice;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
