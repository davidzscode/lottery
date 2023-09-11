package com.qihang.controller.dict.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lihv
 * @since 2023/8/7
 */
@Data
public class DeleteAliPayDictDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "租户id", required = true)
    @NotNull(message = "租户id不能为空")
    private Integer tenantId;
    
    @ApiModelProperty(value = "通道id", required = true)
    @NotNull(message = "通道id不能为空")
    private Integer channelId;
}
