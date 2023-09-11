package com.qihang.controller.ballgame.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-03 13:08
 */
@Data
public class BallGameVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "url")
    private String url;

}
