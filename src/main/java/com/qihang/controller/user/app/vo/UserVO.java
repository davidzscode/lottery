package com.qihang.controller.user.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qihang
 */
@Data
public class UserVO extends BaseVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "0.男 1.女 2.未知")
    private String sex;

    @ApiModelProperty(value = "邀请二维码")
    private String qrCode;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "0 未实名 1 实名")
    private String isReal;

    @ApiModelProperty(value = "是否是代理 0 否 1 是")
    private String isAgent;

    @ApiModelProperty(value = "彩金余额")
    private BigDecimal gold;

    @ApiModelProperty(value = "关注")
    private Integer follow;

    @ApiModelProperty(value = "粉丝")
    private Integer fans;

    @ApiModelProperty(value = "奖金余额")
    private BigDecimal price;

    @ApiModelProperty(value = "创建事件")
    private Date createTime;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "银行账号")
    private String bankNumber;
    
    @ApiModelProperty(value = "银行名称")
    private String bankName;
    
    @ApiModelProperty(value = "银行账号")
    private String zfbNumber;
    
    @ApiModelProperty(value = "0正常 1封禁")
    private String status;
    
    @ApiModelProperty(value = "用户微信")
    private String wechat;
    
    @ApiModelProperty(value = "店主微信")
    private String shopkeeperWechat;
}
