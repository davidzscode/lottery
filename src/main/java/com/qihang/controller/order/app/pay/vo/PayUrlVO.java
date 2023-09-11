package com.qihang.controller.order.app.pay.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qihang
 * @since 2022-10-13
 */
@Data
public class PayUrlVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "支付地址")
    private String url;

}
