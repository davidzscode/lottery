package com.qihang.controller.order.app.lottery.dto;

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
public class LotteryOrderDTO extends PageDTO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "状态 -1全部 0 待出票 1 待开奖 2 未中奖 3 待派奖 4 已派奖")
    private String state;
    
    @ApiModelProperty(value = "userId")
    private Integer userId;
    
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
}
