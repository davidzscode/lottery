package com.qihang.controller.documentary.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author qihang
 * @since 2022-10-05
 */
@Data
@Accessors(chain = true)
public class CreateDocumentaryUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "倍数")
    @NotNull(message = "倍数不能为空")
    private Integer multiple;

    @ApiModelProperty(value = "订单id")
    @NotNull(message = "订单id不能为空")
    private Integer orderId;

    @ApiModelProperty(value = "跟单id")
    @NotNull(message = "跟单id不能为空")
    private Integer documentaryId;
}
