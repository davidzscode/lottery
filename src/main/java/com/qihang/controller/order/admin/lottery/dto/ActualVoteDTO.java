package com.qihang.controller.order.admin.lottery.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-28 1:28
 */
@Data
public class ActualVoteDTO implements Serializable {

    private Integer id;

    @ApiModelProperty(value = "票据")
    @NotBlank(message = "票据不能为空")
    private String bill;
}
