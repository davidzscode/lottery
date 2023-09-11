package com.qihang.controller.omit.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qihang
 * @since 2023-04-07
 */
@Data
public class OmitVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "类型 3 排列3 4排列5 5七星彩 8 大乐透")
    private String type;

    @ApiModelProperty(value = "数据")
    private String record;

}
