package com.qihang.controller.withdrawal.admin.dto;

import com.qihang.common.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-08 21:10
 */
@Data
public class WithdrawalQueryDTO extends PageDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "0 待审核 1 已打款 2 拒绝")
    private String state;

    @ApiModelProperty(value = "0 支付宝 1 银行卡")
    private String type;

    @ApiModelProperty(value = "起始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

}
