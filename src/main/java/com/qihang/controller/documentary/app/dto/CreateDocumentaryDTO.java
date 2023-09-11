package com.qihang.controller.documentary.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author qihang
 * @since 2022-10-05
 */
@Data
@Accessors(chain = true)
public class CreateDocumentaryDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "订单主键id")
    @NotNull(message = "订单主键id不能为空")
    private Integer lotteryOrderId;
    
    @ApiModelProperty(value = "状态 0 公开 1赛后可见")
    @NotBlank(message = "状态不能为空")
    private String state;
    
    @ApiModelProperty(value = "描述")
    @NotNull(message = "描述不能为空")
    private String describe;
    
    @ApiModelProperty(value = "佣金")
    private Integer commission;
}
