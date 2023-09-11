package com.qihang.controller.order.app.lottery.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: qihang
 * @description:
 * @time: 2023-03-26 14:07
 */
@Data
public class YesterdayCentreOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "提示信息")
    private String msg;

}
