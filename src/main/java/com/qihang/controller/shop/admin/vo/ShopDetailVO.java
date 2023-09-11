package com.qihang.controller.shop.admin.vo;

import com.qihang.common.vo.BaseVO;
import com.qihang.controller.ballgame.admin.vo.BallGameWithTenantVO;
import com.qihang.controller.dict.vo.SysDictVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author lihv
 * @since 2023/8/7
 */
@Data
@NoArgsConstructor
public class ShopDetailVO extends BaseVO {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "id")
    private Integer id;
    
    @ApiModelProperty(value = "名称")
    private String name;
    
    @ApiModelProperty(value = "logo")
    private String logo;
    
    @ApiModelProperty(value = "店铺余额")
    private BigDecimal balance;
    
    @ApiModelProperty(value = "扣款比率")
    private BigDecimal shopRate;
    
    @ApiModelProperty(value = "上下架")
    private String line;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @ApiModelProperty(value = "支付通道")
    private List<SysDictVO> channels;
    
    private List<BallGameWithTenantVO> ballGames;
    
    public ShopDetailVO(boolean b, String errorCode, String msg) {
        super(b, errorCode, msg);
    }
}
