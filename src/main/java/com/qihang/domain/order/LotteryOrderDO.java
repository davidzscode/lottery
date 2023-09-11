package com.qihang.domain.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 彩票订单
 *
 * @author qihang
 * @since 2022-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_lottery_order")
public class LotteryOrderDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单id
     */
    private String orderId;


    /**
     * 租户id
     */
    private Integer tenantId;
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 订单状态 0 待出票 1 待开奖 2 未中奖 3 待派奖 4 已派奖
     */
    private String state;

    /**
     * 中奖金额
     */
    private BigDecimal winPrice;

    /**
     * 预测奖金
     */
    private BigDecimal forecast;

    /**
     * 投注金额
     */
    private BigDecimal price;

    /**
     * 目标id
     */
    private String targetIds;

    /**
     * 0 足彩 1 篮彩 2 北京单场 3 排列3
     */
    private String type;

    /**
     * 实体票据
     */
    private String bill;

    /**
     * 方案详情
     */
    private String schemeDetails;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 出票时间
     */
    private Date ticketingTime;


}
