package com.qihang.controller.shop.admin.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-13 11:31
 */
@Data
public class AddShopVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "店铺id")
    private Integer id;
}
