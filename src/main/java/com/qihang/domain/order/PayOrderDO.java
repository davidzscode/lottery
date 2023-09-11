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
 * @author qihang
 * @since 2022-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_pay_order")
public class PayOrderDO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 订单id
     */
    private String orderId;
    
    /**
     * 订单类型 0 充值 1购买足球 2 购买篮球 3.购买北单 4排列三 5.提现 6.竞彩足球派奖 7.竞彩篮球派奖 8. 北京单场派奖 9.排列3派奖 10 竞彩足球退票 11 竞彩篮球退票 12 北京单场退票 13 排列3退票
     */
    private String type;
    
    /**
     * 支付方式 0 支付宝 1.微信 2.启航app
     */
    private String payType;
    
    /**
     * 用户id
     */
    private Integer userId;
    
    /**
     * 订单状态 0 待支付 1 已支付 2.已打款
     */
    private String state;
    
    /**
     * 金额
     */
    private BigDecimal price;
    
    
    /**
     * 租户id
     */
    private Integer tenantId;
    
    /**
     * 通道id
     */
    private Integer channelId;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    
}
