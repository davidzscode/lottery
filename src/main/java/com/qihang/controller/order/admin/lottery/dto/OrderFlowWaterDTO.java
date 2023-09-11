package com.qihang.controller.order.admin.lottery.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-28 1:28
 */
@Data
public class OrderFlowWaterDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer tenantId;

    @ApiModelProperty(value = "起始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

}
