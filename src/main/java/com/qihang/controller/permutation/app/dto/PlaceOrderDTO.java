package com.qihang.controller.permutation.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author: qihang
 * @description:
 * @time: 2022-08-01 17:50
 */
@Data
public class PlaceOrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "百万")
    private List<String> hundredMyriad;

    @ApiModelProperty(value = "十万")
    private List<String> tenMyriad;

    @ApiModelProperty(value = "万")
    private List<String> myriad;

    @ApiModelProperty(value = "千")
    private List<String> kilo;

    @ApiModelProperty(value = "百")
    private List<String> hundred;

    @ApiModelProperty(value = "十")
    private List<Object> ten;

    @NotEmpty(message = "个不能为空")
    @ApiModelProperty(value = "个", required = true)
    private List<Object> individual;

    @NotBlank(message = "投注方式不能为空")
    @ApiModelProperty(value = "投注方式 0 直选 1 组三 2 组六", required = true)
    private String mode;

    @NotNull(message = "注数不能为空")
    @ApiModelProperty(value = "注数", required = true)
    private Integer notes;

    @NotNull(message = "倍数不能为空")
    @ApiModelProperty(value = "倍数", required = true)
    private Integer times;

    @ApiModelProperty(value = "组合")
    private String schemeDetails;
}
