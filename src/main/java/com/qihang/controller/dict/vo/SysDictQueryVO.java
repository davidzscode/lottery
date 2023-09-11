package com.qihang.controller.dict.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qihang
 * @since 2022-11-14
 */
@Data
public class SysDictQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "描述")
    private String dictDesc;

    @ApiModelProperty(value = "数据值")
    private String value;
}
