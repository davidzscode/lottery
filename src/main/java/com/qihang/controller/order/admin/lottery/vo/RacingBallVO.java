package com.qihang.controller.order.admin.lottery.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qihang
 * @since 2022-10-24
 */
@Data
public class  RacingBallVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "注数")
    private Integer notes;

    @ApiModelProperty(value = "类型 2 二串一 3 串一 4 四 串一 等等")
    private String type;

    @ApiModelProperty(value = "倍数")
    private Integer times;

    @ApiModelProperty(value = "赛果")
    private String reward;

    @ApiModelProperty(value = "投注内容")
    private String content;

    @ApiModelProperty(value = "编号")
    private String no;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
