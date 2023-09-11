package com.qihang.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-12 15:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "起始页", required = true)
    @NotNull(message = "起始页不能为空")
    private Integer pageNo;

    @ApiModelProperty(value = "页大小", required = true)
    @NotNull(message = "页大小不能为空")
    private Integer pageSize;


    @ApiModelProperty(value = "排序字段")
    private String sortField;


    @ApiModelProperty(value = "排序规则")
    private String sortRules;
}