package com.qihang.controller.documentary.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-11 21:56
 */
@Data
public class DocumentaryUserVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "跟单金额")
    private BigDecimal price;

}
