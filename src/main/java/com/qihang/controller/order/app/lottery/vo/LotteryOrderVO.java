package com.qihang.controller.order.app.lottery.vo;

import com.qihang.common.vo.BaseVO;
import com.qihang.controller.permutation.app.vo.PermutationRecordVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: qihang
 * @description:
 * @time: 2022-10-12 15:38
 */
@Data
public class LotteryOrderVO extends BaseVO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "体彩名称")
    private String ballName;
    
    @ApiModelProperty(value = "体彩logo")
    private String ballUrl;
    
    @ApiModelProperty(value = "id")
    private Integer id;
    
    @ApiModelProperty(value = "订单id")
    private String orderId;
    
    @ApiModelProperty(value = "订单状态 0 待出票 1 待开奖 2 未中奖 3 待派奖 4 已派奖")
    private String state;
    
    @ApiModelProperty(value = "中奖金额")
    private BigDecimal winPrice;
    
    @ApiModelProperty(value = "投注金额")
    private BigDecimal price;
    
    @ApiModelProperty(value = "票据")
    private String bill;
    
    @ApiModelProperty(value = "预测奖金")
    private BigDecimal forecast;
    
    @ApiModelProperty(value = "0 足彩 1 篮彩 2 北京单场 3 排列3")
    private String type;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @ApiModelProperty(value = "出票时间")
    private Date ticketingTime;
    
    @ApiModelProperty(value = "注数")
    private Integer notes;
    
    @ApiModelProperty(value = "倍数")
    private Integer times;
    
    @ApiModelProperty(value = "订单是否是跟单状态")
    private Boolean documentaryFlag;
    
    @ApiModelProperty(value = "比赛截止时间")
    private Date deadline;
    
    @ApiModelProperty(value = "跟单公开类型")
    private Boolean openFlag;
    
    @ApiModelProperty(value = "比赛是否截止")
    private Boolean isEnd;
    
    @ApiModelProperty(value = "方案详情")
    private String schemeDetails;
    
    @ApiModelProperty(value = "类型")
    private List<Integer> pssTypeList;
    
    @ApiModelProperty(value = "排列3下注列表")
    private List<PermutationRecordVO> recordList;
    
    @ApiModelProperty(value = "竞彩下注列表")
    private List<BallInfoVO> ballInfoList;
    
    private Integer userId;
    
    @ApiModelProperty(value = "昵称")
    private String nickname;
    
    private Integer moni;

    private Integer fd;

    private Integer fdh;

    private Integer gd;

    private Integer gdh;
}
