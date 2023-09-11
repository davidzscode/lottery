package com.qihang.controller.user.app.vo;

import com.qihang.common.vo.BaseVO;
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
public class UserTokenVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "令牌")
    private String token;

    @ApiModelProperty(value = "刷新token")
    private String refreshToken;

    @ApiModelProperty(value = "用户")
    private UserVO user;
}
