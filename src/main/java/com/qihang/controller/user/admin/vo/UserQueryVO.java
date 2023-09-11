package com.qihang.controller.user.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qihang
 */
@Data
public class UserQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "uid")
    private Integer uid;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "0.男 1.女 2.未知")
    private String sex;

    @ApiModelProperty(value = "彩金")
    private BigDecimal gold;

    @ApiModelProperty(value = "奖金")
    private BigDecimal price;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "0 正常 1.禁用")
    private String status;

    @ApiModelProperty(value = "是否是代理 0 否 1 是")
    private String isAgent;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "0 未实名 1 实名")
    private String isReal;

    @ApiModelProperty(value = "二维码地址")
    private String qrCode;

    @ApiModelProperty(value = "上级")
    private String parentName;

    @ApiModelProperty(value = "pid")
    private Integer pid;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "地区")
    private String area;
}
