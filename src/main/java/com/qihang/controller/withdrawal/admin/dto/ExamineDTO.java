package com.qihang.controller.withdrawal.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-08 21:10
 */
@Data
public class ExamineDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空")
    private Integer id;

    @ApiModelProperty(value = "1 已打款 2 拒绝", required = true)
    @NotBlank(message = "审核状态不能为空")
    private String state;
}
