package com.qihang.controller.permutation.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: qihang
 * @description:
 * @time: 2022-08-01 17:50
 */
@Data
public class PermutationRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "期号")
    private Integer stageNumber;

    @ApiModelProperty(value = "出奖结果")
    private List<String> rewardList;

    @ApiModelProperty(value = "投注方式 0 直选 1 组三 2 组九")
    private String mode;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private String reward;

    @ApiModelProperty(value = "百万")
    private String hundredMyriad;

    @ApiModelProperty(value = "十万")
    private String tenMyriad;

    @ApiModelProperty(value = "万")
    private String myriad;

    @ApiModelProperty(value = "千")
    private String kilo;

    @ApiModelProperty(value = "百")
    private String hundred;

    @ApiModelProperty(value = "十")
    private String ten;

    @ApiModelProperty(value = "个")
    private String individual;
}
