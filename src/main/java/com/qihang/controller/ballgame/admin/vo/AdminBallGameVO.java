package com.qihang.controller.ballgame.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-03 13:08
 */
@Data
public class AdminBallGameVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "上下线状态 0 上线 1下线")
    private String line;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
