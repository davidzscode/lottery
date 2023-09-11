package com.qihang.controller.user.admin.dto;

import com.qihang.common.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-07 14:04
 */
@Data
public class UserStatisticsDTO extends PageDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "父级id")
    private String pid;
}
