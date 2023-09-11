package com.qihang.controller.documentary.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-11 21:56
 */
@Data
public class DocumentaryDetailsVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "粉丝")
    private Integer fans;

    @ApiModelProperty(value = "发单数量")
    private Integer issueCount;

    @ApiModelProperty(value = "奖金")
    private BigDecimal bonus;

    @ApiModelProperty(value = "7日盈利")
    private BigDecimal profit;

    @ApiModelProperty(value = "7日命中")
    private String hit;

    @ApiModelProperty(value = "近五场战绩")
    private List<Boolean> recordList;

    @ApiModelProperty(value = "true 关注 false未关注")
    private Boolean isFollow;

    @ApiModelProperty(value = "跟单列表")
    private List<DocumentaryInfoVO> documentaryInfoList;
}
