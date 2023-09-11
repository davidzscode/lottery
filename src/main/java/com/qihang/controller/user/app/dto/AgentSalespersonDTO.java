package com.qihang.controller.user.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-22 18:25
 */
@Data
public class AgentSalespersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "用户昵称或者手机号")
    private String param;
    
    @ApiModelProperty(value = "起始时间")
    private Date startTime;
    
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    
    @ApiModelProperty(value = "店主id")
    private Integer shoperId;
    
}
