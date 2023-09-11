package com.qihang.controller.user.admin.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author lihv
 * @since 2023/8/6
 */
@Data
public class GetUserDetailVO extends BaseVO {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "用户id")
    private Integer id;
    
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    
    @ApiModelProperty(value = "uid")
    private Integer uid;
    
    @ApiModelProperty(value = "手机号")
    private String phone;
    
    @ApiModelProperty(value = "头像")
    private String avatar;
    
    @ApiModelProperty(value = "昵称")
    private String nickname;
    
    @ApiModelProperty(value = "0.男 1.女 2.未知")
    private String sex;
    
    @ApiModelProperty(value = "彩金")
    private BigDecimal gold = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "奖金")
    private BigDecimal price = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "余额")
    private BigDecimal balance = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "姓名")
    private String name;
    
    @ApiModelProperty(value = "0 正常 1.禁用")
    private String status;
    
    @ApiModelProperty(value = "用户级别，0用户，1店主，2销售")
    private String isAgent;
    
    @ApiModelProperty(value = "是否是模拟用户，1是，0不是")
    private Integer moni;
    
    @ApiModelProperty(value = "销售分佣比率")
    private BigDecimal saleRate;

    @ApiModelProperty(value = "中奖分佣比例")
    private Integer commissionRate;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @ApiModelProperty(value = "上级")
    private String parentName;
    
    @ApiModelProperty(value = "pid")
    private Integer pid;
    
    @ApiModelProperty(value = "店铺id")
    private Integer tenantId;
    
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    
    @ApiModelProperty(value = "今日充值")
    private BigDecimal todayRecharge = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总充值")
    private BigDecimal totalRecharge = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "今日提现")
    private BigDecimal todayWithdrawal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总提现")
    private BigDecimal totalWithdrawal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "最近五场投注记录")
    private List<String> recentlyLotteryStateList;
    
    @ApiModelProperty(value = "他的销售人数")
    private Integer sumSalePersons;
    
    @ApiModelProperty(value = "总客户数量")
    private Integer sumCustomer;
    
    @ApiModelProperty(value = "总订单数量")
    private Integer sumOrders;
    
}
