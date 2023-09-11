package com.qihang.controller.order.app.pay.dto;

import com.qihang.common.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author lihv
 * @since 2023/7/29
 */
@Data
public class PayOrderListDTO extends PageDTO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "订单类型")
    @NotEmpty
    private String type;
    
    @ApiModelProperty(value = "订单状态")
    @NotEmpty
    private String state;
    
    @ApiModelProperty(value = "支付方式 0 支付宝 1.微信 2.启航app")
    private String payType = "2";
    
    @ApiModelProperty(value = "起始时间")
    private Date startTime;
    
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    
    private Integer tenantId;
    
}
