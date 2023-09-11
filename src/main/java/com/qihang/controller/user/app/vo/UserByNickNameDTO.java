package com.qihang.controller.user.app.vo;

import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-11 17:47
 */
@Data
public class UserByNickNameDTO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "nickname")
    private String nickname;
}
