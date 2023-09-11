package com.qihang.controller.withdrawal.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-08 22:07
 */
@Data
public class RecordVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "状态 0 待审核 1 已打款 2 拒绝")
    private String state;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
