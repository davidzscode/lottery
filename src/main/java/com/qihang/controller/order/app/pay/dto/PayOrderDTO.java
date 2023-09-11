package com.qihang.controller.order.app.pay.dto;

import com.qihang.common.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-12 15:38
 */
@Data
public class PayOrderDTO extends PageDTO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "订单类型 0 充值 1.足球 2篮球 3.北京单场 4.排列3")
    private String type;
    
    @ApiModelProperty(value = "userId")
    private Integer userId;
    
    @ApiModelProperty(value = "起始时间")
    private Date startTime;
    
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
}
