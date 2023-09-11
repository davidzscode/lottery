package com.qihang.controller.statistics.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lihv
 * @since 2023/7/29
 */
@Data
public class CountShopPriceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "起始时间")
    private Date startTime;
    
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    
    private Integer tenantId;
    
}
