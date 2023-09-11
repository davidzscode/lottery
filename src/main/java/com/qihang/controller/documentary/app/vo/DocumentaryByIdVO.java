package com.qihang.controller.documentary.app.vo;

import com.qihang.common.vo.BaseVO;
import com.qihang.controller.order.app.lottery.vo.BallInfoVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-11 21:56
 */
@Data
public class DocumentaryByIdVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "描述")
    private String describe;

    @ApiModelProperty(value = "截止时间")
    private Long endTime;

    @ApiModelProperty(value = "订单状态")
    private String orderState;

    @ApiModelProperty(value = "彩票名称")
    private String name;

    @ApiModelProperty(value = "0 可以跟单 1不可以跟单")
    private String type;

    @ApiModelProperty(value = "佣金")
    private Integer commission;

    @ApiModelProperty(value = "状态 0 公开 1赛后可见")
    private String state;

    @ApiModelProperty(value = "logo")
    private String url;

    @ApiModelProperty(value = "自购金额")
    private BigDecimal price;

    @ApiModelProperty(value = "跟单总金额")
    private BigDecimal totalDocumentaryPrice;

    @ApiModelProperty(value = "启投金额")
    private BigDecimal riseThrowPrice;

    @ApiModelProperty(value = "7日盈利")
    private BigDecimal profit;

    @ApiModelProperty(value = "7日命中")
    private String hit;

    @ApiModelProperty(value = "true 关注 false未关注")
    private Boolean isFollow;

    @ApiModelProperty(value = "跟单用户")
    private List<DocumentaryUserVO> documentaryUserList;

    @ApiModelProperty(value = "注数")
    private Integer notes;

    @ApiModelProperty(value = "倍数")
    private Integer times;

    @ApiModelProperty(value = "类型")
    private List<Integer> pssTypeList;

    @ApiModelProperty(value = "竞彩下注列表")
    private List<BallInfoVO> ballInfoList;

    @ApiModelProperty(value = "比赛截止时间")
    private Date deadline;
}
