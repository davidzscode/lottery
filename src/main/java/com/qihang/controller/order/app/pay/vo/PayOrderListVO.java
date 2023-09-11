package com.qihang.controller.order.app.pay.vo;

import com.qihang.common.vo.CommonListVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author qihang
 * @since 2022-10-13
 */
@Data
public class PayOrderListVO<T> extends CommonListVO<T> {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "金额")
    private BigDecimal sumPrice;
    
}
