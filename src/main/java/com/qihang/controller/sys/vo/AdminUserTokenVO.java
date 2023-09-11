package com.qihang.controller.sys.vo;

import com.qihang.common.vo.BaseVO;
import com.qihang.controller.shop.admin.vo.AdminShopVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-13 12:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserTokenVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "令牌")
    private String token;

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "店铺信息")
    private AdminShopVO adminShop;
}
